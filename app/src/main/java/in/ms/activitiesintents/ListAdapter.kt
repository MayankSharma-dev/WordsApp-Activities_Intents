package `in`.ms.activitiesintents

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.AccessibilityDelegate
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class ListAdapter(private val listener:CustomOnClick) : RecyclerView.Adapter<ListAdapter.ItemHolder>() {

    // Generates a [CharRange] from 'A' to 'Z' and converts it to a list
    private val list = ('A').rangeTo('Z').toList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_example,parent,false)

        // Setup custom accessibility delegate to set the text read
        view.accessibilityDelegate = Accessibility
        return ItemHolder(view,listener)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val current = list[position]
        holder.button.setText(current.toString())
        holder.button2.text = "Namaste"
    }


    override fun getItemCount(): Int {
        return list.size
    }

    class ItemHolder(view:View,listener: CustomOnClick) : RecyclerView.ViewHolder(view) {

        var button:MaterialButton
        val button2:MaterialButton



        init{
            button = view.findViewById(R.id.example_button)
            button2  = view.findViewById(R.id.example_button2)

            button.setOnClickListener {
                Log.d("Button1","Clicked")
                val position = adapterPosition
                if(position!=RecyclerView.NO_POSITION){
                    listener.onItemClick(button.text.toString())
                }
            }

            button2.setOnClickListener {
                Log.d("Button","Clicked")
                val position = adapterPosition
                if(position!=RecyclerView.NO_POSITION){
                    listener.onItemClick2(position)
                }
            }

        }

    }





    interface CustomOnClick{
        fun onItemClick(str:String)
        fun onItemClick2(pos:Int)
    }

    // Setup custom accessibility delegate to set the text read with
    // an accessibility service
    companion object Accessibility : View.AccessibilityDelegate() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(
            host: View,
            info: AccessibilityNodeInfo
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            // With `null` as the second argument to [AccessibilityAction], the
            // accessibility service announces "double tap to activate".
            // If a custom string is provided,
            // it announces "double tap to <custom string>".
            val customString = host.context?.getString(R.string.look_up_words)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info.addAction(customClick)
        }
    }

}

