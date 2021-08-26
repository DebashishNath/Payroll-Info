import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import { makeStyles, Dialog } from '@material-ui/core';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import Typography from '@material-ui/core/Typography';
import TestForm from '../TestComponents/TestForm'

const useStyles = makeStyles({
  newPosOfDialog: {
    position: "absolute",
    top: "25%",
    left: "40%",
    transform: "translate(-50%, -50%)",
  }
});

const styles = (theme) => ({
  root: {
    margin: 0,
    padding: theme.spacing(2),
    width:'50px',
    height:'20px'
  },
  closeButton: {
    position: 'absolute',
    right: theme.spacing(1),
    top: theme.spacing(1),
    color: theme.palette.grey[500],
  }
});

const DialogTitle = withStyles(styles)((props) => {
  const { children, classes, onClose, ...other } = props;
  return (
    <MuiDialogTitle disableTypography className={classes.root} {...other}>
      <Typography variant="h6">{children}</Typography>
      {onClose ? (
        <IconButton aria-label="close" className={classes.closeButton} onClick={onClose}>
          <CloseIcon />
        </IconButton>
      ) : null}
    </MuiDialogTitle>
  );
});

const DialogContent = withStyles((theme) => ({
  root: {
    padding: theme.spacing(2),
  },
}))(MuiDialogContent);


export default function ModalTestForm({children,title}) {
  const [open, setOpen] = React.useState(true);

  const handleClose = () => {
    setOpen(false);
  };

  const classes = useStyles();

  return (
    <div>
      <Dialog classes={{ paper: classes.newPosOfDialog }} aria-labelledby="customized-dialog-title" open={open}>
        <DialogTitle id="customized-dialog-title" onClose={handleClose}>
          {title}
        </DialogTitle>
        <DialogContent dividers style={{height:'200px'}}>
          <TestForm></TestForm>
        </DialogContent>
      </Dialog>
    </div>
  );
}