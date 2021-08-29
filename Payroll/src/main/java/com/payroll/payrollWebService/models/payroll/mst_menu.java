package com.payroll.payrollWebService.models.payroll;

import com.payroll.payrollWebService.payload.response.MessageResponse;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="mst_menu")
public class mst_menu {
    @Id
    private Long menu_id;
    private String menu_name;
    private String menu_label;
    private String menu_icon;
    private String is_divider;

    @Transient
    private MessageResponse returnMessage;

    public mst_menu() {}

    public Long getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Long menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_label() {
        return menu_label;
    }

    public void setMenu_label(String menu_label) {
        this.menu_label = menu_label;
    }

    public String getMenu_icon() {
        return menu_icon;
    }

    public void setMenu_icon(String menu_icon) {
        this.menu_icon = menu_icon;
    }

    public String getIs_divider() {
        return is_divider;
    }

    public void setIs_divider(String is_divider) {
        this.is_divider = is_divider;
    }

    public MessageResponse getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(MessageResponse returnMessage) {
        this.returnMessage = returnMessage;
    }
}