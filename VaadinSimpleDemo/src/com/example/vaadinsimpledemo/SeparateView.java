package com.example.vaadinsimpledemo;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;

public class SeparateView extends CustomComponent {
	
	Label label1 = new Label();
	Label label2 = new Label();
	Label label3 = new Label();
	
	public SeparateView(){
		Layout layout = new HorizontalLayout();
		
		layout.addComponent(label1);
		layout.addComponent(label2);
		layout.addComponent(label3);
		
		setCompositionRoot(layout);
	}
}
