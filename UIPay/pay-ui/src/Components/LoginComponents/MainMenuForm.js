import React from 'react';
import HomeIcon from "@material-ui/icons/Home";
import ReceiptIcon from "@material-ui/icons/Receipt";
import ReportIcon from '@material-ui/icons/Report';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import Sidebar from "./Sidebar";
import { BrowserRouter as Router, Route } from "react-router-dom";
import CategoryForm from "./CategoryForm";
import DepartmentForm from "./DepartmentForm"

export default class MainMenuForm extends React.Component {
  
  constructor(props) 
  {
    super(props);
  }

  render(){
    
    function onClick(e, item) {}
      
    const items = [
    { name: "master", label: "Master", Icon: HomeIcon,
        items: [
          { name: "category", label: "Category", route: '/category', onClick },
          { name: "department", label: "Department",route: '/department', onClick },
          { name: "designation", label: "Designation",route: '/designation', onClick },
          { name: "earndedcomponents", label: "Earn Ded Component",route: '/earndedcomponents', onClick }
        ] },
        "divider",
        {
          name: "transaction",
          label: "Transaction",
          Icon: ReceiptIcon,
          items: [
            { name: "salarystructure", label: "Salary Structure",route: '/salarystructure', onClick },
            { name: "monthlyattendance", label: "Monthly Attendance",route: '/monthlyattendance', onClick },
            { name: "generatepayslip", label: "Generate Payslip",route: '/generatepayslip', onClick },
          ]
        },
        "divider",
        {
          name: "reports",
          label: "Reports",
          Icon: ReportIcon,
          items: [
            { name: "attendance", label: "Attendance",route: '/attendance', onClick },
            { name: "payslip", label: "payslip",route: '/payslip', onClick }
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
          <Router>
            <Sidebar items={items} />
            <switch>
              <Route path='/category' exact component={CategoryForm} />
              <Route path='/department' exact component={DepartmentForm} />
            </switch>
          </Router>
        </div>
      );
  }
}