import React,{Component} from 'react';
import { Table, TextField , Button,Paper,Select,MenuItem } from '@material-ui/core';

class EmployeeOfficialForm extends Component {

  constructor(props) 
  {
    super(props);
    this.state = {
      categoriesToDisplay:[],
      departmentsToDisplay:[],
      designationsToDisplay:[],
      categoryId:0,
      departmentId:0,
      designationId:0
    }
    this.updateEmployeeOfficial=this.updateEmployeeOfficial.bind(this);
    this.categoryChange = this.categoryChange.bind(this);
    this.departmentChange = this.departmentChange.bind(this);
    this.designationChange = this.designationChange.bind(this);
  }

  componentDidMount(){
    var url='http://192.168.43.241:8086/api/catagories';
    this.populateCombos('Category',url);

    url='http://192.168.43.241:8086/api/departments';
    this.populateCombos('Department',url);

    url='http://192.168.43.241:8086/api/designations';
    this.populateCombos('Designation',url);

    let empId= localStorage.getItem('employeeId');
    if(empId > 0)
    {
      this.populateOfficialEmpInfo(empId);
    }
  }

  async populateOfficialEmpInfo(empId)
  {
    const requestOptions = {
      crossDomain:true,
      method: 'GET',
      headers: { 'Content-Type': 'application/json',
                  'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
    };
    var url='http://192.168.43.241:8086/api/employee/' + empId;
    const response = await fetch(url,requestOptions);
    var data = await response.json();
    if(data!=null)
    {
      document.getElementById("EmpName").innerHTML=data.emp_first_name + ' ' + data?.emp_middle_name + ' ' + data.emp_last_name;
      document.getElementById("AADHARNo").value=data.aadhar_no;
      document.getElementById("PANNo").value=data.pan_no;
      document.getElementById("PFNo").value=data.pf_no;
      document.getElementById("ESINo").value=data.esi_no;
      
      this.setState({categoryId : data.category?.category_id});
      this.setState({departmentId : data.department?.department_id});
      this.setState({designationId : data.designation?.designation_id});
    }
  }

  async populateCombos(comboName,url) 
  {
    try
    {
      let initialDataToDisplay = [];

      const requestOptions = {
        crossDomain:true,
        method: 'GET',
        headers: { 'Content-Type': 'application/json',
                    'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
      };
      
      const response = await fetch(url,requestOptions);
      var data = await response.json();
      if(data!=null && data.length>0){
       
        for(var i=0;i<=data.length-1;i++)
        {
          if(comboName === 'Category')
          {
            initialDataToDisplay.push(<MenuItem value={data[i].category_id}>{data[i].category_name}</MenuItem>)
          }
          if(comboName === 'Department')
          {
            initialDataToDisplay.push(<MenuItem value={data[i].department_id}>{data[i].department_name}</MenuItem>)
          }
          if(comboName === 'Designation')
          {
            initialDataToDisplay.push(<MenuItem value={data[i].designation_id}>{data[i].designation_name}</MenuItem>)
          }
        }
        if(comboName === 'Category')
        {
            this.setState({
              categoriesToDisplay: initialDataToDisplay
            });
        }
        if(comboName === 'Department')
        {
            this.setState({
              departmentsToDisplay: initialDataToDisplay
            });
        }
        if(comboName === 'Designation')
        {
            this.setState({
              designationsToDisplay: initialDataToDisplay
            });
        }
      }
    }
    catch(err) {
      alert(err.message);
    }
  }

  categoryChange(event) 
  {
    this.setState({
      categoryId : event.target.value
    });
  }

  departmentChange(event) 
  {
    this.setState({
      departmentId : event.target.value
    });
  }

  designationChange(event) 
  {
    this.setState({
      designationId : event.target.value
    });
  }

  async updateEmployeeOfficial()
  {
    let categoryId=null;
    if(this.state.categoryId>0)
    {
      categoryId=this.state.categoryId;
    }
    let departmentId=null;
    if(this.state.departmentId>0)
    {
      departmentId=this.state.departmentId;
    }
    
    let designationId=null;
    if(this.state.designationId>0)
    {
      designationId=this.state.designationId;
    }
    
    const requestOptions = 
    {
      crossDomain:true,
      method: 'POST',
      headers: { 'Content-Type': 'application/json',
                  'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
      body: JSON.stringify({
          "emp_id"      :   localStorage.getItem('employeeId'),
          "aadhar_no"   :   document.getElementById("AADHARNo").value,
          "pan_no"      :   document.getElementById("PANNo").value,
          "pf_no"       :   document.getElementById("PFNo").value,
          "esi_no"      :   document.getElementById("ESINo").value,
          "category"    :   {"category_id"    : categoryId},
          "department"  :   {"department_id"  : departmentId},
          "designation" :   {"designation_id" : designationId}
	    })
    };

    var url='http://192.168.43.241:8086/api/updateemployeeofficial';
    
    try 
    {
      const response = await fetch(url,requestOptions);
      var data = await response.json();
      document.getElementById('returnMessage').innerHTML = data.message;
      if (data.code === 0)
      {
        document.getElementById('returnMessage').style="color:green";
      }
      else
      {
        document.getElementById('returnMessage').style="color:red";
      }
    } catch(err) 
    { alert(err.message); }
  }

  render()
  {
    const paperStyle={padding:20,height:'90vh',width:600,margin:"10px auto"}
    const btnStyle={margin:'8px 0'}
   
    return (
      <div>
        <Paper style={paperStyle} variant="outlined">
          <label id = "returnMessage"></label>
          <Table>
            <tr><td><label>Employee Name</label></td>
                <td><label id ="EmpName"></label></td>
            </tr><br/>
            <tr><td><label>AADHAR No</label></td>
                <td><TextField id="AADHARNo" variant='outlined'></TextField></td>
            </tr><br/>
            <tr><td><label>PAN No</label></td>
                <td><TextField id="PANNo" variant='outlined'></TextField></td>
            </tr><br/>
            <tr><td><label>PF No</label></td>
                <td><TextField id="PFNo" variant='outlined'></TextField></td>
            </tr><br/>
            <tr><td><label>ESI No</label></td>
                <td><TextField id="ESINo" variant='outlined'></TextField></td>
            </tr><br/>
            <tr><td><label>Category</label></td>
                <td>
                  <Select id="categoriesCombo" value={this.state.categoryId} onChange={this.categoryChange}
                    style={{ border: '1px solid' ,width:'200px' }}>
                    {this.state.categoriesToDisplay}
                  </Select>
                </td>
            </tr><br/>
            <tr><td><label>Department</label></td>
              <td>
                <Select id="departmentsCombo" value={this.state.departmentId} onChange={this.departmentChange}
                  style={{ border: '1px solid' ,width:'200px' }}>
                  {this.state.departmentsToDisplay}
                </Select>
               </td>
            </tr><br/>
            <tr><td><label>Designation</label></td>
              <td>
                <Select id="designationsCombo" value={this.state.designationId} onChange={this.designationChange}
                  style={{ border: '1px solid' ,width:'200px' }}>
                  {this.state.designationsToDisplay}
                </Select>
               </td>
            </tr><br/>
            <tr><td></td>
                <td><Button type='submit' color='primary' variant='contained' style={btnStyle} 
                  onClick={() => { this.updateEmployeeOfficial() }}>Save</Button></td>
            </tr>
          </Table>
        </Paper>
      </div>
    );
  }
}
export default EmployeeOfficialForm;