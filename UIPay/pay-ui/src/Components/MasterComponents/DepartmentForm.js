import React, { Component } from 'react';
import MasterForm from './MasterForm';

class DepartmentForm extends Component 
{
    constructor(props) 
    {
        super(props);
        this.state = 
        {
            FormName    :'Department',
            LabelCode   :'Department Code',
            LabelName   :'Department Name',
            DeptUrl     :'http://192.168.43.241:8086/api/adddepartment'
        }
    }
    render(){
        return (
            <div>
                <MasterForm FormName={this.state.FormName} LabelCode={this.state.LabelCode} 
                    LabelName={this.state.LabelName} UpdateMasterUrl={this.state.DeptUrl}/>
            </div>
        );
    }
}

export default DepartmentForm;