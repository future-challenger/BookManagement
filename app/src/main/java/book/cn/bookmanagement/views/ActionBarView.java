package book.cn.bookmanagement.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        TextView titleTextView = (TextView) v.findViewById(R.id.titleView);

        leftButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ActionBarView);
        if (a != null) {
            boolean hideLeftButton = a.getBoolean(R.styleable.ActionBarView_hideLeftButton, false);
            if (!hideLeftButton) {
                leftButton.setVisibility(View.VISIBLE);
                
                String leftString = a.getString(R.styleable.ActionBarView_leftButtonTitle);
                leftButton.setText(leftString);
            } else {
                leftButton.setVisibility(View.INVISIBLE);
            }

            boolean hideRightButton = a.getBoolean(R.styleable.ActionBarView_hideRightButton, false);
            if (!hideRightButton) {
                rightButton.setVisibility(View.VISIBLE);

                String rightString = a.getString(R.styleable.ActionBarView_rightButtonTitle);
                rightButton.setText(rightString);
            } else {
                rightButton.setVisibility(View.INVISIBLE);
            }

            boolean hideTitleTextView = a.getBoolean(R.styleable.ActionBarView_hideTitleString, false);
            if (!hideTitleTextView) {
                titleTextView.setVisibility(View.VISIBLE);

                String titleString = a.getString(R.styleable.ActionBarView_titleString);
                titleTextView.setText(titleString);
            } else {
                titleTextView.setVisibility(View.INVISIBLE);
            }
        }
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
