import React from 'react';
import HomeIcon from "@material-ui/icons/Home";
import ReceiptIcon from "@material-ui/icons/Receipt";
import ReportIcon from '@material-ui/icons/Report';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import Sidebar from "./Sidebar";
import { BrowserRouter as Router, Route } from "react-router-dom";
import Grid from "@material-ui/core/Grid";
import CategoryForm from "../MasterComponents/CategoryForm";
import DepartmentForm from "../MasterComponents/DepartmentForm";
import DesignationForm from "../MasterComponents/DesignationForm";
import EarnDedComponentsForm from "../MasterComponents/EarnDedComponentsForm";
import ListEmployeesForm from '../MasterComponents/ListEmployeesForm';
import EmployeeForm from '../MasterComponents/EmployeeForm';
import EmployeeSalaryStructureForm from '../TransactionComponents/EmployeeSalaryStructureForm';
import PaySlipGenerationForm from '../TransactionComponents/PaySlipGenerationForm';
import AttendanceGenerationForm from '../TransactionComponents/AttendanceGenerationForm';
import PrintPaySlipForm from '../ReportComponents/PrintPaySlipForm';
import PrintSingleEmpAttendanceForm from '../ReportComponents/PrintSingleEmpAttendanceForm';
import LogoutForm from '../LogoutComponents/LogoutForm';

export default class MainMenuForm extends React.Component {
  
  render(){
    
    function onClick(e, item) {}
      
    const items = [
    { name: "master", label: "Master", Icon: HomeIcon,
        items: [
          { name: "category", label: "Category", route: '/category', onClick },
          { name: "department", label: "Department",route: '/department', onClick },
          { name: "designation", label: "Designation",route: '/designation', onClick },
          { name: "earndedcomponents", label: "Earn Ded Component",route: '/earndedcomponents', onClick },
          { name: "employee", label: "Employee",route: '/list_employees', onClick }
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
            { name: "attendance", label: "Attendance",route: '/printattendance', onClick },
            { name: "payslip", label: "payslip",route: '/printpayslip', onClick }
          ]
        },
        "divider",
        {
          name: "logout",
          label: "logout",
          Icon: ExitToAppIcon,
          items: [
            { name: "logout", label: "Logout",route: '/logout', onClick }
          ]
        }
      ];
      
      const mainDivStyle = { 
        background: '#CAE4DB',
        backgroundImage: `url('PayrollBackground.jpg')`
      }

      return(
        <div style={mainDivStyle}>
          <Grid container direction="row" spacing="1">
          <Router>
            <Sidebar items={items} />
            <switch>
              <Route path='/category' exact component={CategoryForm} />
              <Route path='/department' exact component={DepartmentForm} />
              <Route path='/designation' exact component={DesignationForm} />
              <Route path='/list_employees' exact component={ListEmployeesForm} />
              <Route path="/employee" exact component={EmployeeForm} />
              <Route path="/earndedcomponents" exact component={EarnDedComponentsForm}></Route>
              <Route path="/salarystructure" exact component={EmployeeSalaryStructureForm} />
              <Route path='/monthlyattendance' exact component={AttendanceGenerationForm}></Route>
              <Route path='/generatepayslip' exact component={PaySlipGenerationForm}></Route>
              <Route path='/printattendance' exact component={PrintSingleEmpAttendanceForm}></Route>
              <Route path='/printpayslip' exact component={PrintPaySlipForm}></Route>
              <Route path='/logout' exact component={LogoutForm}></Route>
            </switch>
          </Router>
          </Grid>
        </div>
      );
  }
}