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

function successButton(text1,text2){
    Swal.fire(
        text1,
        text2,
        'success'
    );
}

function confirm(text){
    Swal.fire({
        title: text,
        showDenyButton: true,
        confirmButtonText: `OK`,
        denyButtonText: `NO`,
      }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
          return true;
        } else if (result.isDenied) {
          return false;
        }
      })
}

export default { error, success,successButton,confirm} ;