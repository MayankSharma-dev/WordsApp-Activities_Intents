package `in`.ms.activitiesintents

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class WordAdapter(private val str: String, context:Context, private val listener:CustomButtonClick) : RecyclerView.Adapter<WordAdapter.ItemHolder>() {

    private val filteredWords: List<String>

    init {
        // Retrieve the list of words from res/values/arrays.xml
        val words = context.resources.getStringArray(R.array.words).toList()

        filteredWords = words.filter { it.startsWith(str,true) }
            .shuffled() // Returns a collection that it has shuffled in place
            .take(5) // Returns the first n items as a [List]
            .sorted() // Returns a sorted version of that [List]

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ItemHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val current = filteredWords[position]
        holder.button.setText(current)
    }

    override fun getItemCount(): Int {
        return filteredWords.size
    }



    class ItemHolder(view: View, listener: CustomButtonClick): RecyclerView.ViewHolder(view){
         val button:MaterialButton
        init {
            button = view.findViewById(R.id.item_button)
            button.setOnClickListener {
                val position = adapterPosition
                if(position!=RecyclerView.NO_POSITION){
                    listener.onItemClicked(button.text.toString())
                }
            }
        }

    }

    interface CustomButtonClick{
        fun onItemClicked(string:String)
    }

}