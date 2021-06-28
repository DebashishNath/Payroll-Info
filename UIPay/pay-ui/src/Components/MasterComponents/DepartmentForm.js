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
            SaveDeptUrl :'http://192.168.43.241:8086/api/savedepartment',
            ListDeptUrl :'http://192.168.43.241:8086/api/masterdatas/Department'
        }
    }
    render(){
        return (
            <div>
                <MasterForm FormName={this.state.FormName} LabelCode={this.state.LabelCode} 
                    LabelName={this.state.LabelName} UpdateMasterUrl={this.state.SaveDeptUrl}
                    ListMasterUrl={this.state.ListDeptUrl}/>
            </div>
        );
    }
}

export default DepartmentForm;