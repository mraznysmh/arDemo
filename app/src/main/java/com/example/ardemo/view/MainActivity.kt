package com.example.ardemo.view

import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ardemo.R
import com.example.ardemo.viewModel.MainViewModel
import com.google.ar.sceneform.ux.ArFragment

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    val fragment: ArFragment?=null
    val fm: FragmentManager=getFragmentManager()
    val transaction:FragmentTransaction=fm.beginTransaction()


//    FragmentManager fm = getSupportFragmentManager();
//    FragmentTransaction transaction = fm.beginTransaction();
//    transaction.replace(R.id.contentFragment, fragment);
//    transaction.commit();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val cameraButton: Button = findViewById(R.id.cameraButton)

        cameraButton.setOnClickListener {
//            val arView= Intent(this, arActivity::class.java)
//            startActivity(arView)
        }
        supportFragmentManager.inTransaction{
            replace(R.id.fragmentContainer, arActivity())
        }
//        transaction.replace(R.id.fragmentContainer,fragment)
//        transaction.commit()

    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

}

