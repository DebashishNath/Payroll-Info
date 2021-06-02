import React, { Component } from 'react';
import { Table, TextField , Button,Paper } from '@material-ui/core';

class CategoryForm extends Component {
  constructor(props) 
  {
    super(props);
    this.addCategory = this.addCategory.bind(this);
  }

  async addCategory() {
        
    const requestOptions = {
        crossDomain:true,
        method: 'POST',
        headers: { 'Content-Type': 'application/json',
                    'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
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
  
  render() {
    const paperStyle={padding:20,height:'25vh',width:400,margin:"40px 100px",backgroundColor:'lightblue'}
    const btnStyle={margin:'8px 0'}
      
    return (
      <div>
        <Paper style={paperStyle} variant="outlined">
          <label id = "returnMessage"></label>
          <Table>
            <tr><td><label>Category Code</label></td>
                <td><TextField id="categoryCode" variant='outlined'></TextField></td>
            </tr><br/>
            <tr><td><label>Category Name</label></td>
                <td><TextField id="categoryName" variant='outlined'></TextField></td>
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