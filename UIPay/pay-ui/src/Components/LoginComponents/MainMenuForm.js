import React from 'react';
import HomeIcon from "@material-ui/icons/Home";
import ReceiptIcon from "@material-ui/icons/Receipt";
import ReportIcon from '@material-ui/icons/Report';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
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
          Icon: ReportIcon,
          items: [
            { name: "attendance", label: "Attendance", onClick },
            { name: "payslip", label: "payslip", onClick }
          ]
        },
        "divider",
        {
          name: "logout",
          label: "logout",
          Icon: ExitToAppIcon
        }
      ];
      
      return(
        <div>
            <Sidebar items={items} />
        </div>
      );
  }
}