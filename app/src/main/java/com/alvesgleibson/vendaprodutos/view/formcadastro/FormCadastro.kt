package com.alvesgleibson.vendaprodutos.view.formcadastro


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils

import com.alvesgleibson.vendaprodutos.databinding.ActivityFormCadastroBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException


class FormCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate( layoutInflater )
        setContentView(binding.root)

        binding.btCadastrar.setOnClickListener { view ->

            var nome = binding.editNome.text.toString()
            var email = binding.editEmail.text.toString()
            var senha = binding.editSenha.text.toString()

            if ( validarCamposCadastro(nome, email, senha) ){

                auth.createUserWithEmailAndPassword( email, senha ).addOnCompleteListener { resultadoCadastro ->

                    if ( resultadoCadastro.isSuccessful ){
                        val snackBar = Snackbar.make( view, "Cadastro realizado com sucesso", Snackbar.LENGTH_SHORT )
                        snackBar.setBackgroundTint(Color.BLUE)
                        snackBar.show()

                        binding.editNome.setText("")
                        binding.editEmail.setText("")
                        binding.editSenha.setText("")

                    }


                }.addOnFailureListener { excepion ->

                    val mensagemErro = when(excepion){

                        is FirebaseAuthWeakPasswordException -> "Digite uma senha de no minimo 6 caracteres"
                        is FirebaseAuthInvalidCredentialsException -> "Digite um email valido"
                        is FirebaseAuthUserCollisionException -> "Email ja Cadastrado"
                        is FirebaseNetworkException -> "Sem conexão com a internet"
                        else -> "Erro ao cadastrar usuario"


                    }

                    var snackbarErro = Snackbar.make(view, mensagemErro, Snackbar.LENGTH_SHORT)
                    snackbarErro.setBackgroundTint( Color.RED )
                    snackbarErro.show()

                }

            }

        }

    }

    fun validarCamposCadastro(nome: String, email: String, senha: String): Boolean {
        var validado = false

        if ( TextUtils.isEmpty( nome )) {

            binding.editNome.error = "Campo nome não pode ser vazio"
            binding.editNome.requestFocus()

        }else {

            if (TextUtils.isEmpty( email )) {

                binding.editEmail.error = "Campo Email não pode ser vazio"
                binding.editEmail.requestFocus()

            } else {

                if (TextUtils.isEmpty( senha )) {

                    binding.editSenha.error = "Campo Senha não pode ser vazio"
                    binding.editSenha.requestFocus()


                }else{
                    validado = true
                }
            }

        }

        return validado
    }



}