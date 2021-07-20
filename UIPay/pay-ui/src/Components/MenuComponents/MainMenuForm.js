import React from 'react';
import { Button} from '@material-ui/core';
import HomeIcon from "@material-ui/icons/Home";
import ReceiptIcon from "@material-ui/icons/Receipt";
import ReportIcon from '@material-ui/icons/Report';
import Sidebar from "./Sidebar";
import { BrowserRouter as Router, Route } from "react-router-dom";
import Grid from "@material-ui/core/Grid";
import CategoryForm from "../MasterComponents/CategoryForm";
import DepartmentForm from "../MasterComponents/DepartmentForm";
import DesignationForm from "../MasterComponents/DesignationForm";
import EarnDedComponentsForm from "../MasterComponents/EarnDedComponentsForm";
import HolidayForm from "../MasterComponents/HolidayForm";
import ListEmployeesForm from '../MasterComponents/ListEmployeesForm';
import EmployeeForm from '../MasterComponents/EmployeeForm';
import EmployeeSalaryStructureForm from '../TransactionComponents/EmployeeSalaryStructureForm';
import ListEmployeeLeaveForm from '../TransactionComponents/ListEmployeeLeaveForm';
import EmployeeLeaveForm from '../TransactionComponents/EmployeeLeaveForm';
import PaySlipGenerationForm from '../TransactionComponents/PaySlipGenerationForm';
import AttendanceGenerationForm from '../TransactionComponents/AttendanceGenerationForm';
import PrintPaySlipForm from '../ReportComponents/PrintPaySlipForm';
import PrintSingleEmpAttendanceForm from '../ReportComponents/PrintSingleEmpAttendanceForm';
import PrintPaysheetForm from '../ReportComponents/PrintPaySheetForm';
import background from "../MenuComponents/PayrollBackground.jpg"

export default class MainMenuForm extends React.Component {

  doLogout()
  {
    localStorage.clear();
    window.location.href = '/';
  }

  render(){
    
    function onClick(e, item) {}
      
    const items = [
    { name: "master", label: "Master", Icon: HomeIcon,
        items: [
          { name: "category", label: "Category", route: '/category', onClick },
          { name: "department", label: "Department",route: '/department', onClick },
          { name: "designation", label: "Designation",route: '/designation', onClick },
          { name: "holiday", label: "Holiday",route: '/holiday', onClick },
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
            { name: "listempleave", label: "Leave",route: '/listempleave', onClick },
            { name: "generateattendance", label: "Generate Attendance",route: '/generateattendance', onClick },
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
            { name: "payslip", label: "Payslip",route: '/printpayslip', onClick },
            { name: "paysheet", label: "Paysheet",route: '/printpaysheet', onClick }
          ]
        }
      ];
      
      return(
        <div style={{ backgroundImage: `url(${background})`}}>
          <div align='right' style={{color :'black',background:'MediumSpringGreen'}}>
                Welcome {localStorage.getItem('userName')} 
                &nbsp;&nbsp;&nbsp;&nbsp; {localStorage.getItem('TodayDate')}
                &nbsp;&nbsp;&nbsp;&nbsp;
                <Button color="secondary" align='right'
                  onClick={() => { this.doLogout() }}>Logout</Button>
          </div>
          <Grid container direction="row" spacing="1">
          <Router>
            <Sidebar items={items} />
            <switch>
              <Route path='/category' exact component={CategoryForm} />
              <Route path='/department' exact component={DepartmentForm} />
              <Route path='/designation' exact component={DesignationForm} />
              <Route path='/holiday' exact component={HolidayForm} />
              <Route path='/list_employees' exact component={ListEmployeesForm} />
              <Route path="/employee" exact component={EmployeeForm} />
              <Route path="/earndedcomponents" exact component={EarnDedComponentsForm}></Route>
              <Route path="/salarystructure" exact component={EmployeeSalaryStructureForm} />
              <Route path="/listempleave" exact component={ListEmployeeLeaveForm} />
              <Route path="/empleave" exact component={EmployeeLeaveForm} />
              <Route path='/generateattendance' exact component={AttendanceGenerationForm}></Route>
              <Route path='/generatepayslip' exact component={PaySlipGenerationForm}></Route>
              <Route path='/printattendance' exact component={PrintSingleEmpAttendanceForm}></Route>
              <Route path='/printpayslip' exact component={PrintPaySlipForm}></Route>
              <Route path='/printpaysheet' exact component={PrintPaysheetForm}></Route>
            </switch>
          </Router>
          </Grid>
        </div>
      );
  }
}