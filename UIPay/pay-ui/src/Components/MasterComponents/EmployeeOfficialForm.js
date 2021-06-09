import React,{Component} from 'react';
import { Table, TextField , Button,Paper,
  Select,MenuItem } from '@material-ui/core';

class EmployeeOfficialForm extends Component {

  constructor(props) 
  {
    super(props);
    this.state = {
      categoriesToDisplay:[],
      departmentsToDisplay:[],
      designationsToDisplay:[]
    }
    this.categoryChange = this.categoryChange.bind(this);
  }

  componentDidMount(){
    var url='http://192.168.43.241:8086/api/catagories';
    this.populateCombos('Category',url);

    url='http://192.168.43.241:8086/api/departments';
    this.populateCombos('Department',url);

    url='http://192.168.43.241:8086/api/designations';
    this.populateCombos('Designation',url);
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
  {}

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
                <td><label></label></td>
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
                  <Select id="categoriesCombo" value={this.state.value} onChange={this.categoryChange}
                    style={{ border: '1px solid' }}>
                    {this.state.categoriesToDisplay}
                  </Select>
                </td>
            </tr><br/>
            <tr><td><label>Department</label></td>
              <td>
                <Select id="departmentsCombo" style={{ border: '1px solid' }}>
                {this.state.departmentsToDisplay}
                </Select>
               </td>
            </tr><br/>
            <tr><td><label>Designation</label></td>
              <td>
                <Select id="designationsCombo" style={{ border: '1px solid' }}>
                {this.state.designationsToDisplay}
                </Select>
               </td>
            </tr><br/>
            <tr><td></td>
                <td><Button type='submit' color='primary' variant='contained' style={btnStyle} 
                  onClick={() => { this.addEmployee() }}>Save</Button></td>
            </tr>
          </Table>
        </Paper>
      </div>
    );
  }
}
export default EmployeeOfficialForm;