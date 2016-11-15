import React, {PropTypes} from 'react';
import Button from 'react-bootstrap/lib/Button';
import Modal from 'react-bootstrap/lib/Modal';
import ModalHeader from 'react-bootstrap/lib/ModalHeader';
import ModalTitle from 'react-bootstrap/lib/ModalTitle';
import ModalBody from 'react-bootstrap/lib/ModalBody';
import ProgressBar from 'react-bootstrap/lib/ProgressBar';

const ModalLoading = ({show, now, onClose, onRedirect}) => {

  const isDone = now == 100;

  return (
    <Modal show={show} onHide={onClose}>
      <ModalHeader closeButton>
        { isDone ? <ModalTitle>All items has been added!</ModalTitle> :
          <ModalTitle>We are adding items to the card</ModalTitle>}
      </ModalHeader>
      <ModalBody>
        <ProgressBar active label={`${now}%`} striped bsStyle={isDone ? "success" : "danger"} now={now}/>
      </ModalBody>
      <Modal.Footer>
        { isDone ? <Button bsStyle="danger" onClick={onRedirect}>Go to cart</Button> :
          <Button bsStyle="danger" disabled>Go to cart</Button>
        }
      </Modal.Footer>
    </Modal>
  );
};

ModalLoading.propTypes = {
  now: PropTypes.number.isRequired,
  show: PropTypes.bool.isRequired,
  onClose: PropTypes.func.isRequired,
  onRedirect: PropTypes.func.isRequired
};

export default ModalLoading;

