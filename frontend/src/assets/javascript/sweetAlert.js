import Swal from "sweetalert2";

function error(text){
    Swal.fire({
        position: 'center',
        icon: 'error',
        title: text,
        confirmButtonColor: '#EAC03A',
      });

}

function success(text){
Swal.fire({
    position: 'center',
    icon: 'success',
    title: text,
    showConfirmButton: false,
    timer: 1500,
  });

}

export default { error, success} ;