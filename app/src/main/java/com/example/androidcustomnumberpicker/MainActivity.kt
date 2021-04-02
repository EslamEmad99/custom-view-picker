package com.example.androidcustomnumberpicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.androidcustomnumberpicker.databinding.ActivityMainBinding
import com.yarolegovich.discretescrollview.DSVOrientation
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.Pivot
import com.yarolegovich.discretescrollview.transform.ScaleTransformer

class MainActivity : AppCompatActivity(),
    DiscreteScrollView.OnItemChangedListener<ItemsAdapter.MyViewHolder> {

    private lateinit var binding: ActivityMainBinding
    private val adapter = ItemsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val list = ArrayList<ItemModel>()
        for (i in 1..6) {
            list.add(ItemModel(i==2, i, "name : $i"))
        }
        adapter.swapData(list)
        binding.picker.adapter = adapter

        binding.picker.setOrientation(DSVOrientation.HORIZONTAL); //Sets an orientation of the view
        binding.picker.setOffscreenItems(0); //Reserve extra space equal to (childSize * count) on each side of the view
        binding.picker.setOverScrollEnabled(true);
        binding.picker.setItemTransformer(
            ScaleTransformer.Builder()
                .setMaxScale(1.05f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
                .setPivotY(Pivot.Y.BOTTOM) // CENTER is a default one
                .build()
        )
        binding.picker.addOnItemChangedListener(this)
        binding.picker.scrollToPosition(1)

        //To allow slide through multiple items
        //binding.picker.setSlideOnFling(true)
    }

    override fun onCurrentItemChanged(
        viewHolder: ItemsAdapter.MyViewHolder?,
        adapterPosition: Int
    ) {
        adapter.changeColor(adapterPosition)
        if (!binding.picker.isComputingLayout) {
            adapter.notifyDataSetChanged()
        }
        Toast.makeText(this, adapter.getItemAt(adapterPosition).name, Toast.LENGTH_LONG).show()
    }
}