import React ,{Component,Fragment} from 'react';
import { Tabs,Tab,Paper } from '@material-ui/core';
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
        const allTabs = ['/personal', '/official'];
        const paperStyle={padding:10,height:'120vh',width:650,margin:"40px 100px",border: '5px solid brown'}

        return (
            <Router>
            <div>
                <Paper elevation={0} style={paperStyle} variant="outlined" >
                <Route
                    path="/"
                    render={({ location }) => (
                    <Fragment>
                        <Tabs value={location.pathname}>
                            <Tab label="Personal" value="/personal" component={Link} to={allTabs[0]} />
                            <Tab label="Official" value="/official" component={Link} to={allTabs[1]} />
                        </Tabs>
                        <Switch>
                            <Route path={allTabs[0]} exact component={EmployeePersonalForm}/>
                            <Route path={allTabs[1]} exact component={EmployeeOfficialForm}/>
                        </Switch>
                    </Fragment>
                    )}
                />
                </Paper>
            </div>
            </Router>
        );
    }
}

export default EmployeeForm;