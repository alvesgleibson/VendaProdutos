package com.alvesgleibson.vendaprodutos.view.formcadastro


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.alvesgleibson.vendaprodutos.databinding.ActivityFormCadastroBinding


class FormCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate( layoutInflater )
        setContentView(binding.root)



    }






}