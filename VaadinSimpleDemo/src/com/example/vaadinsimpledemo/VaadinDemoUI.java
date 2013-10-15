package com.example.vaadinsimpledemo;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.FilesystemContainer;
import com.vaadin.data.util.TextFileProperty;
import com.vaadin.server.ClassResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("label1")
public class VaadinDemoUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = VaadinDemoUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	File file = new File("\\Olga\\tmp");
	FilesystemContainer docs = new FilesystemContainer(file);
	ComboBox boxContent = new ComboBox("Documents", docs);
	Label docView = new Label("File content:",ContentMode.TEXT);
	
	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Thank you for clicking"));
			}
		});
		
		layout.addComponent(button);
		layout.addComponent(boxContent);
		layout.addComponent(docView);
		
		System.out.println(file.isDirectory());
		
		boxContent.addValueChangeListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				docView.setPropertyDataSource(new TextFileProperty((File) event.getProperty().getValue()));
				
			}
		});
		boxContent.setImmediate(true);  
		
		// Using SeparateView:
		SeparateView sview = new SeparateView();
		
		sview.label1.setValue("Horizontal layout inside of the vertical.");
		sview.label2.setValue("This is lable 2 :)");
		sview.label2.setStyleName("textstyle");
		
		layout.addComponent(sview);
		
		// Image:
		// Find the application directory
		String basepath = VaadinService.getCurrent()
		                  .getBaseDirectory().getAbsolutePath();

		// Image as a file resource
		FileResource resource = new FileResource(new File(basepath +
		                        "/WEB-INF/images/img26.jpg"));

		// Show the image in the application
		Image image = new Image("Image from \\WEB-INF\\images folder:", resource);
		layout.addComponent(image);
		
		Link link = new Link("Link to the image file", resource);
		layout.addComponent(link);

	}

}