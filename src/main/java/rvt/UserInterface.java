package rvt;

import java.awt.*;
import javax.swing.*;

public class UserInterface {
    private final TodoList list;

    private JFrame frame;
    private JTextField taskField;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;

    public UserInterface(TodoList list) {
        this.list = list;
    }

    public void start() {
        frame = new JFrame("To Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 400);
        frame.setLayout(new BorderLayout());

        // Augšējā daļa: teksta lauks + Add poga
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        taskField = new JTextField();
        JButton addButton = new JButton("Add");

        topPanel.add(taskField, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        // Vidus: uzdevumu saraksts
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Apakša: Remove poga
        JButton removeButton = new JButton("Remove selected");

        // Saliekam visu logā
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(removeButton, BorderLayout.SOUTH);

        // Ielādē jau esošos todo no CSV logā
        refreshTaskList();

        // Kad nospiež Add
        addButton.addActionListener(e -> addTask());

        // Var arī nospiest Enter teksta laukā
        taskField.addActionListener(e -> addTask());

        // Kad nospiež Remove
        removeButton.addActionListener(e -> removeTask());

        frame.setVisible(true);
    }

    private void addTask() {
        String task = taskField.getText().trim();

        if (!task.isEmpty()) {
            list.add(task);
            taskField.setText("");
            refreshTaskList();
        }
    }

    private void removeTask() {
        int selectedIndex = taskList.getSelectedIndex();

        if (selectedIndex != -1) {
            list.remove(selectedIndex + 1);
            refreshTaskList();
        }
    }

    private void refreshTaskList() {
        listModel.clear();

        for (String task : list.getTasks()) {
            listModel.addElement(task);
        }
    }
}