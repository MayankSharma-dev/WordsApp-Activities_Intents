package `in`.ms.activitiesintents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.ms.activitiesintents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ListAdapter.CustomOnClick {

    private lateinit var binding: ActivityMainBinding
    private var isLinearLayoutManager = true

    // This used to create Const values which is available across the app without the need of class object.
    companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLayout()

    }

    private fun setLayout(){
        if (isLinearLayoutManager) {
            binding.rvMain.layoutManager = LinearLayoutManager(this)
        } else {
            binding.rvMain.layoutManager = GridLayoutManager(this, 4)
        }
        binding.rvMain.adapter = ListAdapter(this)
        binding.rvMain.setHasFixedSize(true)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        // Calls code to set the icon based on the LinearLayoutManager of the RecyclerView
        setIcon(layoutButton)

        return true

    }

    // G labsMethod
    private fun setIcon1(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        // Set the drawable for the menu icon based on which LayoutManager is currently in use

        // An if-clause can be used on the right side of an assignment if all paths return a value.
        // The following code is equivalent to
        // if (isLinearLayoutManager)
        //     menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
        // else menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this, R.drawable.baseline_vertical_distribute_24)
            else ContextCompat.getDrawable(this, R.drawable.baseline_grid_view_24)
    }


    // for changing icon of the menuItem
    // put Elvis operator '?' after the formal parameter in the function for checking nullability
    private fun setIcon(menuItem:MenuItem?){
        menuItem?.setIcon( if(isLinearLayoutManager) ContextCompat.getDrawable( this,R.drawable.baseline_vertical_distribute_24)
        else ContextCompat.getDrawable(this,R.drawable.baseline_grid_view_24) )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                // Sets isLinearLayoutManager (a Boolean) to the opposite value
                isLinearLayoutManager = !isLinearLayoutManager
                // Sets layout and icon
                setLayout()
                setIcon(item)

                return true
            }
            //  Otherwise, do nothing and use the core event handling

            // when clauses require that all possible paths be accounted for explicitly,
            //  for instance both the true and false cases if the value is a Boolean,
            //  or an else to catch all unhandled cases.
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onItemClick(str: String) {
        Toast.makeText(this@MainActivity, str, Toast.LENGTH_SHORT).show()

        val i = Intent(this@MainActivity, DetailActivity::class.java)
        i.putExtra(LETTER, str)
        startActivity(i)
    }

    override fun onItemClick2(pos: Int) {
        Toast.makeText(this@MainActivity, "button2", Toast.LENGTH_SHORT).show()
    }



}