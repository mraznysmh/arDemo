package com.example.ardemo.view

import android.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.opengl.GLSurfaceView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ardemo.R
import com.example.ardemo.viewModel.MainViewModel
import com.google.ar.sceneform.ux.ArFragment
import javax.microedition.khronos.opengles.GL10

class MainActivity1 : AppCompatActivity(){



    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    val fragment: ArFragment?=arActivity()
    val fm: FragmentManager= supportFragmentManager
    val transaction:FragmentTransaction=fm.beginTransaction()


//    FragmentManager fm = getSupportFragmentManager();
//    FragmentTransaction transaction = fm.beginTransaction();
//    transaction.replace(R.id.contentFragment, fragment);
//    transaction.commit();
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        //fragment=arActivity()
//        transaction.add(R.id.fragmentContainer, fragment)
//        transaction.commit()
//        supportFragmentManager.inTransaction{
//            replace(R.id.fragmentContainer, arActivity())
//        }
//        transaction.replace(R.id.fragmentContainer,fragment)
//        transaction.commit()
//    }


    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

}

