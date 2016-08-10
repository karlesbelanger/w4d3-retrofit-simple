package ca.kgb.retrofitsimple;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.design.widget.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by admin on 8/8/2016.
 */
public class LibuAdapter extends RecyclerView.Adapter<LibuAdapter.ViewHolder> {

    String[] mDataSet = {"Data"};
    private ArrayList<String> mString;
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textViewName;
        public IMyViewHolderClicks mListener;

        //pass my listener in param
        public ViewHolder(View itemView, IMyViewHolderClicks listener) {
            super(itemView);
            mListener = listener;
            //Set up the listener on each item and the view
            textViewName = (TextView)itemView.findViewById(R.id.r_list_text);
            textViewName.setOnClickListener(this);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            if(view instanceof TextView){
                mListener.onTextViewListener(view);
//                Snackbar.make(findViewById(android.R.id.content), "Snackbar chez Raymon", Snackbar.LENGTH_LONG)
//                        .setAction("Undo", mListener)
//                        .setActionTextColor(Color.RED)
//                        .show();

            }
        }
        //create interface listener for my objects
        public static interface IMyViewHolderClicks{
            public void onTextViewListener(View caller);
        }
    }

    public LibuAdapter(ArrayList<String> strings){
        this.mString = strings;
    }
    @Override
    public LibuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);

        LibuAdapter.ViewHolder viewHolder = new ViewHolder(view, new ViewHolder.IMyViewHolderClicks() {
            public final String TAG = LibuAdapter.class.getSimpleName();

            @Override
            public void onTextViewListener(View caller) {
                Snackbar snackbar = Snackbar
                        .make(caller, "Snackbar chez Raymon", Snackbar.LENGTH_SHORT);

                snackbar.show();
                Log.d(TAG, "onTextViewListener: " + "We are in my recycle view listener");
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LibuAdapter.ViewHolder holder, int position) {
        String string = mString.get(position);

        TextView textView = holder.textViewName;
        textView.setText(string);


        //holder.textViewName.setText(mDataSet[position]);
    }
//        ///////////////////////////////////////////
//        final String element = mDataset[position];
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onClickSubject.onNext(element);
//            }
//        });
//    }
//
//    public Observable<String> getPositionClicks(){
//        return onClickSubject.asObservable();
//    }

    @Override
    public int getItemCount() {
        return mString == null
                ? 0
                : mString.size();
    }
}
