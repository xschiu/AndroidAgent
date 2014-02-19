package com.va.androidagent;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class ExpandableTextView extends TextView {
    private static final int DEFAULT_TRIM_LENGTH = 60;
    private static final String ELLIPSIS = ".....";
 
    private CharSequence originalText;
    private CharSequence trimmedText;
    private BufferType bufferType;
    private boolean trim = true;
    private int trimLength;
 
    public ExpandableTextView(Context context) {
        this(context, null);
    }
 
    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
 
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView);
        this.trimLength = typedArray.getInt(R.styleable.ExpandableTextView_trimLength, DEFAULT_TRIM_LENGTH);
        typedArray.recycle();
 
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trim = !trim;
                setText();
                requestFocusFromTouch();
//            	Intent intent = new Intent(getActivity().getApplicationContext(), ExpandedView.class);	
//				getActivity().startActivity(intent); 
                
//                ExpandedView expandedFrag = new ExpandedView();
//                FragmentManager manager = getFragmentManager();
//                FragmentTransaction transaction= manager.beginTransaction();
//                transaction.add(R.id.my_layout,expandedFrag,"expandedFragment");
//                transaction.commit();
            	
            }
        });
    }
 
  
    private void setText() {
        super.setText(getDisplayableText(), bufferType);
    }
 
    private CharSequence getDisplayableText() {
        return trim ? trimmedText : originalText;
    }
 
    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = text;
        trimmedText = getTrimmedText(text);
        bufferType = type;
        setText();
    }
 
    private CharSequence getTrimmedText(CharSequence text) {
        if (originalText != null && originalText.length() > trimLength) {
            return new SpannableStringBuilder(originalText, 0, trimLength + 1).append(ELLIPSIS);
        } else {
            return originalText;
        }
    }
 
    public CharSequence getOriginalText() {
        return originalText;
    }
 
    public void setTrimLength(int trimLength) {
        this.trimLength = trimLength;
        trimmedText = getTrimmedText(originalText);
        setText();
    }
 
    public int getTrimLength() {
        return trimLength;
    }
}