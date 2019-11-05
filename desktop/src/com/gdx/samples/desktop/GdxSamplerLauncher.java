package com.gdx.samples.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.gdx.samples.common.SampleFactory;
import com.gdx.samples.common.SampleInfos;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class GdxSamplerLauncher extends JFrame {

    private static final int WIDTH = 1080;
    private static final int HEIGHT = 720;
    private static final int CELL_WIDTH = 200;
    private static final int CANVAS_WIDTH = WIDTH - CELL_WIDTH;

    private JList sampleList;
    private JPanel controlPanel;

    // AWT - Abstract Window Toolkit
    private LwjglAWTCanvas lwjglAWTCanvas; // enables to embeed libgdx into java app

    public GdxSamplerLauncher() {
        init();
    }

    private void init(){

        createControlPanel();

        Container container = getContentPane();
        container.add(controlPanel, BorderLayout.WEST);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if(lwjglAWTCanvas != null){
                    lwjglAWTCanvas.stop();
                }
            }
        });

        setTitle(GdxSamplerLauncher.class.getSimpleName());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pack(); // resize and layout components
        setVisible(true);


        //launchSample();
    }

    public void createControlPanel(){
        controlPanel = new JPanel(new GridBagLayout()); // rows and cells layout (table)
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weighty = 1;

        sampleList = new JList(SampleInfos.getSampleNames().toArray());
        sampleList.setFixedCellWidth(CELL_WIDTH);
        sampleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sampleList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2){

                    String sampleName = (String) sampleList.getSelectedValue();

                    if(sampleName.isEmpty() || sampleName == null){

                    }else{
                        launchSample(sampleName);
                    }
                }
            }
        });

        JScrollPane scrollPanel = new JScrollPane(sampleList);
        controlPanel.add(scrollPanel, gbc);

//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.fill = GridBagConstraints.VERTICAL;
//        gbc.weighty = 1;


    }

    private void launchSample(String name){

        Container container = getContentPane();

        if(lwjglAWTCanvas != null){
            lwjglAWTCanvas.stop();
            container.remove(lwjglAWTCanvas.getCanvas());
        }

        ApplicationListener sample = SampleFactory.newSample(name);

        lwjglAWTCanvas = new LwjglAWTCanvas(sample);
        lwjglAWTCanvas.getCanvas().setSize(CANVAS_WIDTH, HEIGHT);
        container.add(lwjglAWTCanvas.getCanvas(), BorderLayout.CENTER);

        pack();
    }

    public static void main(String[] args) {
        // Run the Jframe
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GdxSamplerLauncher();
            }
        });
    }

}
