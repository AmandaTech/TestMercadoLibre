package com.mx.testmercadolibre

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mx.testmercadolibre.base.MLBaseActivity
import com.mx.testmercadolibre.base.MLFragmentInteractorBase
import com.mx.testmercadolibre.expose.MLNavigation
import com.mx.testmercadolibre.utils.MLResource
import com.mx.testmercadolibre.widget.MLDialogFactory


class MLMainActivity : MLBaseActivity(), MLFragmentInteractorBase {

    private lateinit var toolbar: Toolbar
    private lateinit var searchView: SearchView
    private val TAG: String = MLMainActivity::class.java.simpleName
    override fun getLayoutId() = R.layout.activity_main


    companion object{
        val MODE_MAIN = 1
        fun createIntent(contexto: Context, mode: Int): Intent {
            var intent = Intent(contexto, MLMainActivity::class.java)
            intent.putExtra("mode",mode)
            return intent
        }

    }


    override fun start() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        inflater(MLNavigation.getFragmentByEnumChoose(MLNavigation.MLNavigationChoose.ML_LISTPRODUCTS,""))

        if (hasDataConnection(this)) {
            // El dispositivo tiene acceso a Internet
        } else {
            // El dispositivo no tiene acceso a Internet
            openDialog()
        }


    }
    fun openDialog(){

        val txt1 = getString(R.string.st_internet_connection)
        val txt2 = getString(R.string.st_accept)
        MLDialogFactory.createDesPrimaryButton(this, txt1, txt2) {
            Log.d(TAG, "listener: ")
        }

    }
    fun openDialogService(){
        val txt1 = "Tenemos problemas para mostrar la informacion del producto, por favor intente mas tarde"
        val txt2 = getString(R.string.st_accept)
        MLDialogFactory.createDesPrimaryButton(this, txt1, txt2) {
            Log.d(ContentValues.TAG, "listener: ")
        }

    }


    override fun inflater(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.ft_container, fragment)
            .commitAllowingStateLoss()
    }

    override fun modifyDotScreen(int: Int, show: Boolean?) {
    }




    override fun changeFragmentFlow(step: Int,  productsId: String): Fragment {
        val chooser = MLNavigation.MLNavigationChoose.getByStepId(step)
        val fragment = MLNavigation.getFragmentByEnumChoose(chooser,productsId)
        inflater(fragment)
        return fragment
    }

    override fun changeFragment(step: Int, productsId: String) =
        changeFragmentFlow(step + 1,productsId)

    override fun handlerMessageErrorApigee(resource: MLResource<Any>): Boolean {
        /**
         * Muestra errores a nivel servicio**/
        if ( resource.status == MLResource.Status.ERROR && resource.message != null   ) {
            Log.d("error_servicio",resource.message)
            openDialogService()
            return true
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.isNotEmpty() == true){
                    inflater(MLNavigation.getFragmentByEnumChoose(MLNavigation.MLNavigationChoose.ML_LISTPRODUCTS,query))                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        return true
    }

    private fun hasDataConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true &&
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }

    override fun listener() {

    }




}


