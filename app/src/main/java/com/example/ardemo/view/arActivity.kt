package com.example.ardemo.view

import android.app.Fragment
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.crashlytics.android.Crashlytics
import com.example.ardemo.R
import com.google.ar.core.*
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import java.io.IOException
import com.google.ar.core.AugmentedImageDatabase



class arActivity : ArFragment() {
    //private var arFragment: ArFragment? = null
    //var printer: TransformableNode? = null
    //private var printerRenderable: ModelRenderable? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=super.onCreateView(inflater, container,savedInstanceState)

        planeDiscoveryController.hide()
        planeDiscoveryController.setInstructionView(null)
        arSceneView.planeRenderer.isEnabled = false
        arSceneView.isLightEstimationEnabled = false

        initializeSession()

        return view
    }

    override fun getSessionConfiguration(session: Session): Config {

        fun loadAugmentedImageBitmap(imageName: String): Bitmap =
            requireContext().assets.open(imageName).use {
                return BitmapFactory.decodeStream(it)
            }


        fun setupAugmentedImageDatabase(config: Config, session: Session): Boolean {
            try {
                config.augmentedImageDatabase = AugmentedImageDatabase(session).also { db ->
                    db.addImage(VIDEO1, loadAugmentedImageBitmap(IMG4))
//                db.addImage(TEST_VIDEO_2, loadAugmentedImageBitmap(TEST_IMAGE_2))
//                db.addImage(TEST_VIDEO_3, loadAugmentedImageBitmap(TEST_IMAGE_3))
                }
                return true
            } catch (e: IllegalArgumentException) {
                Log.e(TAG, "Could not add bitmap to augmented image database", e)
            } catch (e: IOException) {
                Log.e(TAG, "IO exception loading augmented image bitmap.", e)
            }
            return false
        }

        return super.getSessionConfiguration(session).also {
            it.lightEstimationMode = Config.LightEstimationMode.DISABLED
            it.focusMode = Config.FocusMode.AUTO

            if (!setupAugmentedImageDatabase(it, session)) {
                Toast.makeText(requireContext(), "Failed", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onUpdate(frameTime: FrameTime) {
        val frame = arSceneView.arFrame
        if (frame == null || frame.camera.trackingState != TrackingState.TRACKING) {
            return
        }

        val updatedAugmentedImages = frame.getUpdatedTrackables(AugmentedImage::class.java)
        for (augmentedImage in updatedAugmentedImages) {
            Log.d(TAG, "${augmentedImage.trackingState.name} :: augmentedImage = ${augmentedImage.name}")

            if (augmentedImage.trackingState == TrackingState.TRACKING) {
                Log.d(TAG, "Tracking augmented image [${augmentedImage.name}]")
            }
        }
    }
    //Plaszczyzna
//    fun enableAR() {  //w BigRepie jest ModelEnum jako argument, tutaj moge dodac filmik <?>
//        val check = ArCoreApk.getInstance().checkAvailability(this)
//
//        if (check.isSupported) {
//            //Budowa modelu 3D:
////            val modelResource = when (model) {
////                //ModelEnum.PRINTEREXT -> R.raw.bigrep_pro_lp_v09
////                //ModelEnum.PRINTERINT -> R.raw.bigrep_pro_lp_v06
////                //ModelEnum.TEST -> R.raw.low_poly
////                     }
////            ModelRenderable.builder()
////                .setSource(this, modelResource)
////                .build()
////                .thenAccept{ renderable -> printerRenderable = renderable }
////                .exceptionally { throwable ->
////                    Log.e("render error", "Unable to load Renderable.", throwable)
////                    //Crashlytics.log(Log.ERROR, "Bigrep", throwable.message)
////                    null
////                }
//            //Sprawdzanie powierzchnii
//            arFragment?.setOnTapArPlaneListener { hitResult: HitResult, _: Plane, _: MotionEvent ->
//                // Create the Anchor.
//                val anchor = hitResult.createAnchor()
//                val anchorNode = AnchorNode(anchor)
//                anchorNode.setParent(arFragment?.arSceneView?.scene)
//
//                // Create the transformable andy and add it to the anchor.
//                printer = TransformableNode(arFragment?.transformationSystem)
//                printer?.scaleController?.minScale = 0.2f
//                printer?.setParent(anchorNode)
//               // printer?.renderable = printerRenderable //to raczej nie potrzebne, tylko MovieRenderable potrzebne
//                printer?.select()
//                //TODO:tworzenie sceny/wyświetlanie filmu na scenie<?>
//
//                //printer?.
//            }
//        } else { // Unsupported or unknown.
//            //toast("Device is not supported or unknown")
//            Crashlytics.log(Log.ERROR, "ARDemo2", "Enable AR")
//        }
//    }
//
//    fun movieOnPlaneAR(){
//
//    }
    companion object{
        private const val TAG="arActivity"

        private const val IMG1="blacksquare.jpg"
        private const val IMG2="podladka.jpeg"
        private const val IMG3="traffic.jpg"
        private const val IMG4="highreso.jpg"
        private const val VIDEO1="chicken.mp4"
    }

}

