package uz.eloving.practise


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.item_bottom_sheet, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.rv_regions)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val data = listOf(
            "America",
            "Russian",
            "Pakistan",
            "India",
            "Dubai",
            "Uzbekistan")
        val adapter = RegionAdapter(data)
        recyclerView.adapter = adapter

        return view
    }
}