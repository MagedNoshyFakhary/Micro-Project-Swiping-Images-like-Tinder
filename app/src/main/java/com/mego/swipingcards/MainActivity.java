package com.mego.swipingcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardStackView cardStackView = findViewById(R.id.card_stack_view);
        manager = new CardStackLayoutManager(this, new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {
                Log.d(TAG, "onCardDragging: d=" + direction.name() + " ratio=" + ratio);
            }

            @Override
            public void onCardSwiped(Direction direction) {
                Log.d(TAG, "onCardSwiped: p=" + manager.getTopPosition() + " d=" + direction);
                if (direction == Direction.Right){
                    Toast.makeText(MainActivity.this, "Direction Right", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Top){
                    Toast.makeText(MainActivity.this, "Direction Top", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Left){
                    Toast.makeText(MainActivity.this, "Direction Left", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Bottom){
                    Toast.makeText(MainActivity.this, "Direction Bottom", Toast.LENGTH_SHORT).show();
                }

                // Paginating
                if (manager.getTopPosition() == adapter.getItemCount() - 5){
                    paginate();
                }

            }

            @Override
            public void onCardRewound() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardCanceled() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardAppeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_topSpeed);
                Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.getText());
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_mark);
                Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.getText());
            }
        });
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        adapter = new CardStackAdapter(addList(),this);
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());

    }

    private void paginate() {
        List<ItemModel> old = adapter.getItems();
        List<ItemModel> baru = new ArrayList<>(addList());
        CardStackCallback callback = new CardStackCallback(old, baru);
        DiffUtil.DiffResult hasil = DiffUtil.calculateDiff(callback);
        adapter.setItems(baru);
        hasil.dispatchUpdatesTo(adapter);
    }

    private List<ItemModel> addList() {
        List<ItemModel> items = new ArrayList<>();
        items.add(new ItemModel(R.drawable.sample1, 340, " McLaren ", "720S"));
        items.add(new ItemModel(R.drawable.sample2, 355, "Ferrari", "SF90 Stradale"));
        items.add(new ItemModel(R.drawable.sample3, 360, "Ferrari", "F8 Tributo"));
        items.add(new ItemModel(R.drawable.sample4, 365, "Lamborghini ", "Huracán Evo"));
        items.add(new ItemModel(R.drawable.sample5, 370, "FORD", "GT"));
        items.add(new ItemModel(R.drawable.sample1, 340, " McLaren ", "720S"));
        items.add(new ItemModel(R.drawable.sample2, 355, "Ferrari", "SF90 Stradale"));
        items.add(new ItemModel(R.drawable.sample3, 360, "Ferrari", "F8 Tributo"));
        items.add(new ItemModel(R.drawable.sample4, 365, "Lamborghini ", "Huracán Evo"));
        items.add(new ItemModel(R.drawable.sample5, 370, "FORD", "GT"));
        return items;
    }
}