package `in`.ms.activitiesintents

import android.app.Notification.Action
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import `in`.ms.activitiesintents.MainActivity.Companion.LETTER
import `in`.ms.activitiesintents.MainActivity.Companion.SEARCH_PREFIX
import `in`.ms.activitiesintents.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(), WordAdapter.CustomButtonClick {

    lateinit var binding:ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val str = intent?.getExtras()?.getString(LETTER).toString()



        binding.rvDetail.adapter= WordAdapter(str,this,this)
        binding.rvDetail.setHasFixedSize(true)

    }

    override fun onItemClicked(string: String) {

        val queryUrl: Uri = Uri.parse("$SEARCH_PREFIX $string")
        val intent = Intent(Intent.ACTION_VIEW,queryUrl)
        startActivity(intent)

    }
}