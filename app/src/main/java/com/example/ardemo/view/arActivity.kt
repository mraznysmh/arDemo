package com.example.ardemo.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import com.crashlytics.android.Crashlytics
import com.example.ardemo.R
import com.google.ar.core.ArCoreApk
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

class arActivity : AppCompatActivity() {
    private var arFragment: ArFragment? = null
    var printer: TransformableNode? = null
    private var printerRenderable: ModelRenderable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)

        arFragment = supportFragmentManager.findFragmentById(R.id.ux_fragment) as ArFragment

        enableAR()

    }

    //Plaszczyzna
    fun enableAR() {  //w BigRepie jest ModelEnum jako argument, tutaj moge dodac filmik <?>
        val check = ArCoreApk.getInstance().checkAvailability(this)

        if (check.isSupported) {
            //Budowa modelu 3D:
//            val modelResource = when (model) {
//                //ModelEnum.PRINTEREXT -> R.raw.bigrep_pro_lp_v09
//                //ModelEnum.PRINTERINT -> R.raw.bigrep_pro_lp_v06
//                //ModelEnum.TEST -> R.raw.low_poly
//                     }
//            ModelRenderable.builder()
//                .setSource(this, modelResource)
//                .build()
//                .thenAccept{ renderable -> printerRenderable = renderable }
//                .exceptionally { throwable ->
//                    Log.e("render error", "Unable to load Renderable.", throwable)
//                    //Crashlytics.log(Log.ERROR, "Bigrep", throwable.message)
//                    null
//                }
            //Sprawdzanie powierzchnii
            arFragment?.setOnTapArPlaneListener { hitResult: HitResult, _: Plane, _: MotionEvent ->
                // Create the Anchor.
                val anchor = hitResult.createAnchor()
                val anchorNode = AnchorNode(anchor)
                anchorNode.setParent(arFragment?.arSceneView?.scene)

                // Create the transformable andy and add it to the anchor.
                printer = TransformableNode(arFragment?.transformationSystem)
                printer?.scaleController?.minScale = 0.2f
                printer?.setParent(anchorNode)
               // printer?.renderable = printerRenderable //to raczej nie potrzebne, tylko MovieRenderable potrzebne
                printer?.select()
                //TODO:tworzenie sceny/wyświetlanie filmu na scenie<?>
                //printer?.
            }
        } else { // Unsupported or unknown.
            //toast("Device is not supported or unknown")
            Crashlytics.log(Log.ERROR, "ARDemo2", "Enable AR")
        }
    }

    fun movieOnPlaneAR(){

    }
}

