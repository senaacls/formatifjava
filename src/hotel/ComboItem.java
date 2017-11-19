/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

/**
 *
 * @author daksa
 */
public class ComboItem {
    private String id;
    private String name;

    public ComboItem(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getId()
    {
        return this.id;
    }
    
    public String getName()
    {
        return this.name;
    }

    // Add the getter and setter as you want.

    // This will be used internally by JComboBox as the label to be displayed.
    @Override
    public String toString() {
        return name;
    }
}
