package to.nishant.shoppin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import to.nishant.shoppin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FragmentSearch extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<SearchClass> list;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("allsearch");
        recyclerView = view.findViewById(R.id.result_list);
        searchView = view.findViewById(R.id.search_view);
        searchView.setIconified(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();


        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        if (databaseReference != null) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        SearchClass product = dataSnapshot.getValue(SearchClass.class);
                        list.add(product);

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    if (s.isEmpty()){
                        Toast.makeText(getContext(), "empty", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        search(s);
                    }

                    return true;
                }
            });

        }

    }

    private void search(String str){
        ArrayList<SearchClass> myList = new ArrayList<>();
        for(SearchClass object : list){
            if (object.getName().toLowerCase().contains(str.toLowerCase())) {
                myList.add(object);
            }
        }
        SearchAdapter searchAdapter = new SearchAdapter(getContext(),myList);
        recyclerView.setAdapter(searchAdapter);


    }
}