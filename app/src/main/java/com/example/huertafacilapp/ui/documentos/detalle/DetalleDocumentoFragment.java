package com.example.huertafacilapp.ui.documentos.detalle;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.huertafacilapp.R;
import com.example.huertafacilapp.databinding.FragmentDetalleDocumentoBinding;

public class DetalleDocumentoFragment extends Fragment {

  public static final int CODIGO_PERMISOS = 100;
  public static final String PERMISO_CAMARA = android.Manifest.permission.CAMERA;
  public static final int REQUEST_IMAGE_CAPTURE = 1;
  private DetalleDocumentoViewModel vm;
  private FragmentDetalleDocumentoBinding binding;

  public static DetalleDocumentoFragment newInstance() {
    return new DetalleDocumentoFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    binding = FragmentDetalleDocumentoBinding.inflate(inflater, container, false);
    vm = new ViewModelProvider(this).get(DetalleDocumentoViewModel.class);

    vm.getEditable().observe(getViewLifecycleOwner(), editable -> {
      if(editable){
        if(validarPermisos()){
          binding.etTitulo.setEnabled(true);
          binding.etMensaje.setEnabled(true);
          binding.btnCargarFoto.setEnabled(true);
          binding.btnSubmit.setEnabled(true);
          binding.btnEliminar.setEnabled(false);
        };
      }else{
        binding.etTitulo.setEnabled(false);
        binding.etMensaje.setEnabled(false);
        binding.btnCargarFoto.setEnabled(false);
        binding.btnSubmit.setEnabled(false);
        binding.btnEliminar.setEnabled(true);
      }
    });

    binding.btnEliminar.setOnClickListener(v -> {
      vm.eliminarDocumento(v);
    });

    vm.getError().observe(getViewLifecycleOwner(), error -> {
      if(error != null){
        binding.tvError.setText(error);
      }
    });

    vm.getFotoBitmap().observe(getViewLifecycleOwner(), foto -> {
      if(foto != null){
        binding.ivImagenDocumento.setImageBitmap(foto);
      }
    });

    vm.getDocumento().observe(getViewLifecycleOwner(), documento -> {
      binding.etTitulo.setText(documento.getTitulo());
      binding.etMensaje.setText(documento.getCuerpo());
      if(documento.getFoto() != null){
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(documento.getFoto(),0,documento.getFoto().length);
        binding.ivImagenDocumento.setImageBitmap(imageBitmap);
      }
    });

    binding.cbEditable.setOnCheckedChangeListener((buttonView, isChecked) -> {
      vm.changeEditable();
    });

    binding.btnCargarFoto.setOnClickListener(v -> {
      sacarFoto(v);
    });

    binding.btnSubmit.setOnClickListener(v -> {
      vm.validarInputs(binding.etTitulo.getText().toString(), binding.etMensaje.getText().toString());
    });

    vm.recuperarDirectorio(getArguments());


    return binding.getRoot();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    vm = new ViewModelProvider(this).get(DetalleDocumentoViewModel.class);
    // TODO: Use the ViewModel
  }

  private boolean validarPermisos(){
    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
      return true;
    }

    if(ActivityCompat.checkSelfPermission(requireContext(), PERMISO_CAMARA) == PackageManager.PERMISSION_GRANTED){
      return true;
    }

    if(shouldShowRequestPermissionRationale(PERMISO_CAMARA)){
      cargarDialog();
    }else{
      requestPermissions(new String[]{PERMISO_CAMARA},CODIGO_PERMISOS);
    }

    return false;
  }

  public void sacarFoto(View v){
    Intent sacarFoto = new Intent((MediaStore.ACTION_IMAGE_CAPTURE));
    if(sacarFoto.resolveActivity(getActivity().getPackageManager()) != null){
      startActivityForResult(sacarFoto, REQUEST_IMAGE_CAPTURE);
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    vm.respuestaDeCamara(requestCode, resultCode, data, REQUEST_IMAGE_CAPTURE);
  }

  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    if(requestCode == CODIGO_PERMISOS){
      if(grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
      }else{
        solicitarPermisos();
      }
    }
  }

  private void solicitarPermisos(){
    final CharSequence[] opciones = {"Si","No"};
    final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(requireContext());
    alertOpciones.setTitle("¿Desea configurar los permisos de forma manual?");
    alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        if(opciones[which] == "Si"){
          Intent intent = new Intent();
          intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
          Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
          intent.setData(uri);
          startActivity(intent);
        }else{
          Toast.makeText(getContext(), "Los permisos no fueron aceptados", Toast.LENGTH_SHORT).show();
          dialog.dismiss();
        }
      }
    });
    alertOpciones.show();
  }

  private void cargarDialog(){
    AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
    dialog.setTitle("Permisos Desactivados");
    dialog.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la app");
    dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        requestPermissions(new String[]{PERMISO_CAMARA},CODIGO_PERMISOS);
      }
    });
    dialog.show();
  }
}