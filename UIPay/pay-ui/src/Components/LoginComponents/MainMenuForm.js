import React from 'react';
import HomeIcon from "@material-ui/icons/Home";
import ReceiptIcon from "@material-ui/icons/Receipt";
import SettingsIcon from "@material-ui/icons/Settings";
import Sidebar from "./Sidebar";

export default class MainMenuForm extends React.Component {
  
  render(){
    
    function onClick(e, item) {
        window.alert(JSON.stringify(item, null, 2));
    }
      
      const items = [
        { name: "master", label: "Master", Icon: HomeIcon,
        items: [
          { name: "category", label: "Category", onClick },
          { name: "department", label: "Department", onClick },
          { name: "designation", label: "Designation", onClick },
          { name: "earndedcomponents", label: "Earn Ded Component", onClick }
        ] },
        "divider",
        {
          name: "transaction",
          label: "Transaction",
          Icon: ReceiptIcon,
          items: [
            { name: "salarystructure", label: "Salary Structure", onClick },
            { name: "monthlyattendance", label: "Monthly Attendance", onClick },
            { name: "generatepayslip", label: "Generate Payslip", onClick },
          ]
        },
        "divider",
        {
          name: "reports",
          label: "Reports",
          Icon: SettingsIcon,
          items: [
            { name: "attendance", label: "Attendance", onClick },
            { name: "payslip", label: "payslip", onClick }
          ]
        }
      ];
      
      return(
        <div>
            <Sidebar items={items} />
        </div>
      );
  }
}