import React, { Component } from 'react';
import MasterForm from './MasterForm';
import HelperMethods from '../CommonComponents/HelperMethods';

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
            SaveDeptUrl : HelperMethods.GetServerIP() + 'api/savedepartment',
            ListDeptUrl : HelperMethods.GetServerIP() + 'api/masterdatas/Department'
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