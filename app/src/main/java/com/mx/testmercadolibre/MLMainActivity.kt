package com.mx.testmercadolibre

import android.content.Context
import android.content.Intent
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mx.testmercadolibre.base.MLBaseActivity
import com.mx.testmercadolibre.base.MLFragmentInteractorBase
import com.mx.testmercadolibre.base.ShareDataFragment
import com.mx.testmercadolibre.data.api.ResProducts
import com.mx.testmercadolibre.expose.MLNavigation
import com.mx.testmercadolibre.utils.MLResource


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


    }


    override fun inflater(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.ft_container, fragment)
            .commitAllowingStateLoss()
    }

    override fun modifyDotScreen(int: Int, show: Boolean?) {
    }



    override fun changeFragmentFlow(step: Int,  args: ShareDataFragment?): Fragment {
        val chooser = MLNavigation.MLNavigationChoose.getByStepId(step)
        val fragment = MLNavigation.getFragmentByEnumChoose(chooser,args?.product.toString())
        inflater(fragment)
        return fragment
    }

    override fun changeFragment(step: Int, args: ShareDataFragment?) =
        changeFragmentFlow(step + 1)

    override fun handlerMessageErrorApigee(resource: MLResource<Any>): Boolean {
        if ( resource.status == MLResource.Status.ERROR && resource.message != null   ) {
            //showMessage("" , resource.message)
            // messajeErrorDatoBancario("Aviso" , resource.message)
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
                if (newText?.isNotEmpty() == true){
                    inflater(MLNavigation.getFragmentByEnumChoose(MLNavigation.MLNavigationChoose.ML_LISTPRODUCTS,newText))
                }
                return true
            }
        })

        return true
    }

    override fun listener() {

    }


}