package com.example.navigationcomponent

import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.navigationcomponent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var listener: NavController.OnDestinationChangedListener /** Method to update the appBar with the selected fragment color**/


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.fragment)
        drawerLayout = findViewById(R.id.drawer_layout) /** It can be used view binding instead find view to get the drawerLayout **/
        binding.navigationView.setupWithNavController(navController)//setup the nav_view
        // with the nav controller, wich is the fragment in activity_main.xml

        appBarConfiguration = AppBarConfiguration(navController.graph,drawerLayout)
        setupActionBarWithNavController(navController,appBarConfiguration)

   /*     /** OPTIONAL********Method to update the appBar with the selected fragment color**/

        listener = NavController.OnDestinationChangedListener{controller, destination, arguments ->
            if (destination.id == R.id.listFragment){
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.purple_500)))
            }else if (destination.id == R.id.addFragment){
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.teal_200)))
            }
        }


    */

    }

    override fun onResume() {
        super.onResume()
//        navController.addOnDestinationChangedListener(listener)
        }

    override fun onPause() {
        super.onPause()
        navController.addOnDestinationChangedListener(listener)
    }

    /** Method to enable the nav drawer with the menu**/
    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.fragment)

        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()

    }
}