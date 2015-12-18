package book.cn.bookmanagement.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import book.cn.bookmanagement.R;

/**
 * Created by uncle_charlie on 16/12/15.
 */
public class ActionBarView extends LinearLayout implements View.OnClickListener {

    private OnLeftButtonClickListener _onLeftButtonClickListener;
    private OnRightButtonClickListener _onRightButtonClickListener;

    public ActionBarView(Context context) {
        this(context, null);
    }

    public ActionBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.action_bar_view, this);
        Button leftButton = (Button) v.findViewById(R.id.leftButton);
        Button rightButton = (Button) v.findViewById(R.id.rightButton);

        leftButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.leftButton) {
            if (_onLeftButtonClickListener != null) {
                _onLeftButtonClickListener.onClick(v);
            }
        } else {
            if (_onRightButtonClickListener != null) {
                _onRightButtonClickListener.onClick(v);
            }
        }
    }

    public interface OnLeftButtonClickListener {
        void onClick(View v);
    }

    public interface OnRightButtonClickListener {
        void onClick(View v);
    }
}
