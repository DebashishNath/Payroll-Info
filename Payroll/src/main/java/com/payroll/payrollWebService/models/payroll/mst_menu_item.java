package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;
import javax.persistence.*;

@Entity
@Table(name="mst_menu_item")
public class mst_menu_item {
    @Id
    private Long menu_item_id;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
    private mst_menu menu;

    private String menu_item_name;
    private String menu_item_label;
    private String menu_item_route;
    private String menu_item_action;

    @Transient
    private MessageResponse returnMessage;

    public mst_menu_item() {}

    public Long getMenu_item_id() {
        return menu_item_id;
    }

    public void setMenu_item_id(Long menu_item_id) {
        this.menu_item_id = menu_item_id;
    }

    public mst_menu getMenu() {
        return menu;
    }

    public void setMenu(mst_menu menu) {
        this.menu = menu;
    }

    public String getMenu_item_name() {
        return menu_item_name;
    }

    public void setMenu_item_name(String menu_item_name) {
        this.menu_item_name = menu_item_name;
    }

    public String getMenu_item_label() {
        return menu_item_label;
    }

    public void setMenu_item_label(String menu_item_label) {
        this.menu_item_label = menu_item_label;
    }

    public String getMenu_item_route() {
        return menu_item_route;
    }

    public void setMenu_item_route(String menu_item_route) {
        this.menu_item_route = menu_item_route;
    }

    public String getMenu_item_action() {
        return menu_item_action;
    }

    public void setMenu_item_action(String menu_item_action) {
        this.menu_item_action = menu_item_action;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}