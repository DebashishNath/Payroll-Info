import React, { Component } from 'react';
import { Table, TextField , Button,Paper,Select,MenuItem} from '@material-ui/core';
import MessageBoxForm from '../CommonComponents/MessageBoxForm';
import HelperMethods from '../CommonComponents/HelperMethods';

class PaySlipGenerationForm extends Component {
  constructor(props) 
  {
    super(props);
    this.state = {
      monthsToDisplay:[],
      monthId:0,
      showMessageBox:false,
      title:'',
      displayMessage:''
    }
    this.monthsComboChange = this.monthsComboChange.bind(this);
    this.generatePaySlip = this.generatePaySlip.bind(this);
  }

  async componentDidMount()
  {
    this.PopulateMonths();
    await document.getElementById("year").focus();
  }

  PopulateMonths()
  {
    let initialDataToDisplay = [];

    initialDataToDisplay.push(<MenuItem value={1}>January</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={2}>February</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={3}>March</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={4}>April</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={5}>May</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={6}>June</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={7}>July</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={8}>August</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={9}>September</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={10}>October</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={11}>November</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={12}>December</MenuItem>);

    this.setState({
      monthsToDisplay: initialDataToDisplay
    });
  }

  monthsComboChange(event) 
  {
    this.setState({
      monthId : event.target.value
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
    
    if(document.getElementById("year").value.trim().length === 0)
    {
      await this.showMessage(true,'Error Information','Enter Year for Pay Slip generation','year');
      return false;
    }
    if(isNaN(document.getElementById("year").value.trim()))
    {
      await this.showMessage(true,'Error Information', 'Enter numeric value for Year ','year');
      return false;
    }
    if(this.state.monthId === 0)
    {
      await this.showMessage(true,'Error Information','Select Month for Pay Slip generation','monthsCombo');
      return false;
    }

    return true;
  }

  async generatePaySlip() 
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
                    'Authorization' : 'Bearer ' +  localStorage.getItem('tokenValue') }
    };
    
    var payMonth=this.state.monthId;
    var payYear=document.getElementById("year").value;
    let payDate='';
    if(payMonth<=9)
    {
      payDate=payYear + '-0' +  payMonth + '-01' ;
    }
    else
    {
      payDate=payYear + '-' +  payMonth + '-01' ;
    }
    var url= HelperMethods.GetServerIP() + 'api/payslip/' + payMonth + '/' + payYear + '/' + payDate
    try 
    {
      const response = await fetch(url,requestOptions);
      var data = await response.json();
      
      if (data.code === 0)
      {
        await this.showMessage(true,'Save Information',data.message,'');
      }
      else
      {
        await this.showMessage(true,'Error Information',data.message,'');
      }
    }
    catch(err) 
    {
      await this.showMessage(true,'Error Information',err.message,'');
    }
  }

    render() {
        const paperStyle={padding:20,height:'15vh',width:400,margin:"40px 100px",border: '5px solid brown'}
      
      return (
        <div>
            <Paper style={paperStyle} variant="outlined">
              {this.state.showMessageBox ?
                    <div>
                        <MessageBoxForm title={this.state.title}>
                        {this.state.displayMessage}
                        </MessageBoxForm>
                    </div> : null}
              <Table>
                <tr>
                  <td>
                    <TextField id="year" label='Year' placeholder='Year' variant='outlined' style={{width: '30%'}} inputProps={{ maxLength: 4 }} size="small"></TextField>
                  </td>
                  <td>
                    <Select id="monthsCombo" value={this.state.value} onChange={this.monthsComboChange}
                      style={{ border: '1px solid',width:'150px' }}>
                      {this.state.monthsToDisplay}
                    </Select>
                  </td>
                </tr>
                <tr>
                  <br/>
                  <div><Button type='submit' color='primary' variant='contained'  
                        onClick={() => { this.generatePaySlip() }}>Generate Pay Slip</Button>
                  </div>
                </tr>
              </Table>
            </Paper>
         </div>
        );
  }
}

export default PaySlipGenerationForm;