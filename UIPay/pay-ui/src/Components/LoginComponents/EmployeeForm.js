import React ,{Component,Fragment} from 'react';
import { Tabs,Tab } from '@material-ui/core';
import EmployeePersonalForm from "./EmployeePersonalForm";
import EmployeeOfficialForm from "./EmployeeOfficialForm";
import { Route,Link, BrowserRouter as Router,Switch } from 'react-router-dom';

class EmployeeForm extends Component {
    constructor(props) 
    {
      super(props);
      this.state = {
          value: 0
      };
      this.handleChange = this.handleChange.bind(this);
    }

    handleChange = (event, value) => {
        this.setState({ value });
    };

    render()
    {
        const allTabs = ['/', '/tab2'];

        return (
            <Router>
            <div className="App">
                <Route
                    path="/"
                    render={({ location }) => (
                    <Fragment>
                        <Tabs value={location.pathname}>
                            <Tab label="Personal" value="/" component={Link} to={allTabs[0]} />
                            <Tab label="Official" value="/tab2" component={Link} to={allTabs[1]} />
                        </Tabs>
                        <Switch>
                            <Route path={allTabs[0]} exact component={EmployeePersonalForm}/>
                            <Route path={allTabs[1]} exact component={EmployeeOfficialForm}/>
                        </Switch>
                    </Fragment>
                    )}
                />
            </div>
            </Router>
        );
    }
}

export default EmployeeForm;