import React, { Component } from 'react';
import { Table, TextField , Button,Paper,Select,MenuItem } from '@material-ui/core';
import SaveIcon from '@material-ui/icons/Save';
import MessageBoxForm from '../CommonComponents/MessageBoxForm';
import HelperMethods from '../CommonComponents/HelperMethods';

class CompanyForm extends Component {
    constructor(props) 
    {
        super(props);
        this.state = {
            statesToDisplay:[],
            districtsToDisplay:[],
            stateId:0,
            districtId:0,
            showMessageBox:false,
            title:'',
            displayMessage:''
        }
        this.updateCompanyDetails = this.updateCompanyDetails.bind(this);
        this.statesComboChange = this.statesComboChange.bind(this);
        this.districtsComboChange = this.districtsComboChange.bind(this);
        this.onStateValueChange=this.onStateValueChange.bind(this);
    }

    componentDidMount()
    {
        document.getElementById("companyCode").focus();
        var url= HelperMethods.GetServerIP() + 'api/states';
        this.populateCombos('State',url)
       
        document.getElementById("lblCompanyId").value=1;
        if(document.getElementById("lblCompanyId").value > 0)
        {
          this.populateCompanyInfo(1);
        }
    }

    async populateCompanyInfo(comapnyId)
    {
        const requestOptions = 
        {
            crossDomain:true,
            method: 'GET',
            headers: { 'Content-Type': 'application/json',
                    'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
        };
        var url= HelperMethods.GetServerIP() + 'api/company/' + comapnyId;
        const response = await fetch(url,requestOptions);
        var data = await response.json();
        if(data!=null)
        {
          document.getElementById("companyCode").value = data.company_code;
          document.getElementById("companyName").value = data.company_name;
          document.getElementById("address1").value = data.company_address1;
          document.getElementById("address2").value = data.company_address2;
          this.setState({ stateId : data.district.state.state_id });
          this.populateDistricts(data.district.state.state_id);
          this.setState({districtId : data.district.district_id });
          document.getElementById("pin").value = data.company_pin;
          document.getElementById("contactNumber").value = data.company_contact_number;
          document.getElementById("email").value = data.company_email;
          document.getElementById("tanNo").value = data.company_tan_no;
          var dateFormat = require('dateformat');
          document.getElementById("projectStartDate").value = dateFormat(data.project_start_date,'yyyy-mm-dd');
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
          if(comboName === 'State')
          {
            initialDataToDisplay.push(<MenuItem value={data[i].state_id}>{data[i].state_name}</MenuItem>)
          }
          if(comboName === 'District')
          {
            initialDataToDisplay.push(<MenuItem value={data[i].district_id}>{data[i].district_name}</MenuItem>)
          }
        }
        if(comboName === 'State')
        {
            this.setState({ statesToDisplay: initialDataToDisplay });
        }
        if(comboName === 'District')
        {
            this.setState({ districtsToDisplay: initialDataToDisplay });
        }
      }
    }
    catch(err) 
    {
      await this.showMessage(true,'Error Information',err.message,'');
    }
  }

  statesComboChange(event) 
  {
    var empStateId=event.target.value;
    this.populateDistricts(empStateId);
  }
  
  populateDistricts(empStateId)
  {
    this.setState({
      districtsToDisplay: []
    });
    
    var url= HelperMethods.GetServerIP() + 'api/districts/' + empStateId;
    this.populateCombos('District',url)
    this.setState({stateId:empStateId});
  }

  districtsComboChange(event) 
  {
    this.setState({
      districtId : event.target.value
    });
  }

  onStateValueChange(event) {
    this.setState({
      gender: event.target.value
    });
  }

  async showMessage(isShowMsgBox,messageBoxtitle,dispMessage,controlName) 
  {
      if(controlName.trim().length > 0)
      {
          document.getElementById(controlName).focus();
      }
      await this.setState({
          showMessageBox:isShowMsgBox,
          title: messageBoxtitle,
          displayMessage: dispMessage
      });
  }

  async validateControls()
  {
    await this.showMessage(false,'','','');

    if(document.getElementById("companyCode").value.trim().length === 0)
    {
      await this.showMessage(true,'Error Information', 'Company code cannot be blank','companyCode');
      return false;
    }
    if(document.getElementById("companyName").value.trim().length === 0)
    {
      await this.showMessage(true,'Error Information', 'Company name cannot be blank','companyName');
      return false;
    }
    if(document.getElementById("address1").value.trim().length === 0)
    {
      await this.showMessage(true,'Error Information', 'Address1 cannot be blank','address1');
      return false;
    }
    if(this.state.stateId === 0)
    {
      await this.showMessage(true,'Error Information', 'State cannot be blank','statesCombo');
      return false;
    }
    if(this.state.districtId === 0)
    {
      await this.showMessage(true,'Error Information', 'District cannot be blank','districtsCombo');
      return false;
    }
    if(document.getElementById("pin").value.trim().length === 0)
    {
      await this.showMessage(true,'Error Information', 'Pin cannot be blank','pin');
      return false;
    }
    if(document.getElementById("contactNumber").value.trim().length === 0)
    {
      await this.showMessage(true,'Error Information', 'Contact Numbers cannot be blank','contactNumber');
      return false;
    }
    if(document.getElementById("email").value.trim().length === 0)
    {
      await this.showMessage(true,'Error Information', 'Email Ids cannot be blank','email');
      return false;
    }
    if(document.getElementById("projectStartDate").value.trim().length === 0)
    {
      await this.showMessage(true,'Error Information', 'Project start date should be in DD-MM-YYYY','projectStartDate');
      return false;
    }
    return true;
  }

  async updateCompanyDetails() 
  {
    if(!await this.validateControls())
    {
      return;
    }
    
    const requestOptions = 
    {
      crossDomain:true,
      method: 'POST',
      headers: { 'Content-Type': 'application/json',
                  'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
      body: JSON.stringify({
        "company_id" : document.getElementById("lblCompanyId").value,
	      "company_code" : document.getElementById("companyCode").value,
        "company_name" : document.getElementById("companyName").value,
        "company_address1" : document.getElementById("address1").value,
        "company_address2" : document.getElementById("address2").value,
        "district" : {"district_id" : this.state.districtId},
        "company_pin" : document.getElementById("pin").value,
	      "company_contact_number" : document.getElementById("contactNumber").value,
	      "company_email" : document.getElementById("email").value,
        "company_tan_no" : document.getElementById("tanNo").value,
        "project_start_date" : document.getElementById("projectStartDate").value
	    })
    };
    
    var url= HelperMethods.GetServerIP() + 'api/savecompany';
       
    try 
    {
      const response = await fetch(url,requestOptions);
      var data = await response.json();
      if (data.code === 0)
      {
        await this.showMessage(true,'Save Information', data.message,'');
      }
      else
      {
        await this.showMessage(true,'Error Information', data.message,'');
      }
    } catch(err) 
    { 
      await this.showMessage(true,'Error Information', err.message,'');
    }
  }

  render()
  {
    const paperStyle={padding:20,height:'80vh',width:600,margin:"20px 100px",border: '1px solid blue'}
    const btnStyle={margin:'8px 0'}
   
    return (
      <div>
        {this.state.showMessageBox ?
        <div>
            <MessageBoxForm title={this.state.title}>
            {this.state.displayMessage}
            </MessageBoxForm>
        </div> : null}
        <Paper style={paperStyle} variant="outlined">
          <Table>
            <tr>
              <td><TextField id="companyCode" variant='outlined' label='Code' required  style ={{width: '50%'}} size="small"></TextField></td>
              <td><TextField id="companyName" variant='outlined' label='Company Name' required size="small"></TextField></td>
              <td><label id="lblCompanyId" value='0'></label></td>
            </tr>
            <br/>
            <tr><td><label>Address</label></td>
                <td><TextField id="address1" multiline variant='outlined' style ={{width: '190%'}}></TextField></td>
            </tr><br/>
            <tr><td></td>
                <td><TextField id="address2" multiline variant='outlined' style ={{width: '190%'}}></TextField></td>
            </tr><br/>
            <tr><td><label>State</label></td>
                <td>
                  <Select id="statesCombo" value={this.state.stateId} onChange={this.statesComboChange}
                    style={{ border: '1px solid' ,width:'200px' }}>
                    {this.state.statesToDisplay}
                  </Select>
                </td>
            </tr>
            <tr>
              <td><label>District</label></td>
              <td>
                <Select id="districtsCombo" value={this.state.districtId} onChange={this.districtsComboChange}
                  style={{ border: '1px solid' ,width:'200px'  }}>
                  {this.state.districtsToDisplay}
                </Select>
               </td>
               <td>&emsp;&emsp;<TextField id="pin" variant='outlined' label='Pin' style ={{width: '60%'}} size="small"></TextField></td>
            </tr>
            <br/>
            <tr><td><label>Contact Numbers</label></td>
                <td><TextField id="contactNumber" variant='outlined' style ={{width: '190%'}} size="small"></TextField></td>
            </tr><br/>
            <tr><td><label>Email ids</label></td>
                <td><TextField id="email" variant='outlined' style ={{width: '190%'}} size="small"></TextField></td>
            </tr><br/>
            <tr><td><label>Tan No</label></td>
                <td><TextField id="tanNo" variant='outlined' style ={{width: '190%'}} size="small"></TextField></td>
            </tr><br/>
            <tr>
            <td><label>Project Start Date</label></td>
              <td>
                <TextField id="projectStartDate" label="" type="date" defaultValue=""
                  InputLabelProps={{shrink: true,}} size="small" />
              </td>
              <td><Button type='submit' color='primary' variant='contained' style={btnStyle} 
                  size='small' startIcon={<SaveIcon />} onClick={() => { this.updateCompanyDetails() }}>Save</Button></td>
            </tr>
          </Table>
        </Paper>
      </div>
    );
  }
}

export default CompanyForm;