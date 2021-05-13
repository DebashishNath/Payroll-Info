import React, { Component } from 'react';
import { Table, TextField , Button,Paper } from '@material-ui/core';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import PropTypes from 'prop-types'
import { withStyles } from '@material-ui/core/styles'

class CategoryForm extends Component {
  constructor(props) 
  {
    super(props);
    this.state = {
        accessToken: this.props.history.location.state?.tokenValue
    };
    this.addCategory = this.addCategory.bind(this);
    this.closeCategory = this.closeCategory.bind(this);
  }

  async addCategory() {
        
    const requestOptions = {
        crossDomain:true,
        method: 'POST',
        headers: { 'Content-Type': 'application/json',
                    'Authorization' : 'Bearer ' + this.state.accessToken },
        body: JSON.stringify({
            "category_id" : "0",
            "category_code" : document.getElementById("categoryCode").value,
            "category_name" : document.getElementById("categoryName").value
        })
    };
      
    var url='http://192.168.43.241:8086/api/addcategory';
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
    }
    catch(err) {
      alert(err.message);
    }
  }

  
  closeCategory(){
    alert("Inside closeCategory()");
    
  }

  render() {
      const paperStyle={padding:20,height:'30vh',width:400,margin:"100px auto"}
      const btnStyle={margin:'8px 0'}
      
      return (
      <div>
        <Paper elevation={10} style={paperStyle}>
          <IconButton aria-label="close" align="right" >
          <CloseIcon onClick={() => { this.closeCategory() }}/>
          </IconButton>
          <label id = "returnMessage"></label>
          <Table>
            <tr><td><label>Category Code</label></td>
                <td><TextField id="categoryCode" placeholder='Enter Category Code' variant='outlined'></TextField></td>
            </tr>
            <tr><td><label>Category Name</label></td>
                <td><TextField id="categoryName" placeholder='Enter Category Name' variant='outlined'></TextField></td>
            </tr>
            <tr><td></td>
                <td><Button type='submit' color='primary' variant='contained' style={btnStyle} 
                  onClick={() => { this.addCategory() }}>Save</Button></td>
            </tr>
          </Table>
        </Paper>
      </div>
    );
  }
}

export default CategoryForm;