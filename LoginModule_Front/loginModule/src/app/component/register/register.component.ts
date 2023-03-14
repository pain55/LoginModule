import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { stringify } from 'querystring';
import { Subscriber } from 'rxjs';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/service/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {


  user = {} as User;

  hide:boolean = true;

  constructor(private userService:UserService,private router:Router) { }

  ngOnInit(): void {
  }

  registerForm() {
    this.userService.registerUser(this.user).subscribe(
        (data) => {
          Swal.fire({
            icon: 'success',
            title: 'Your work has been saved',
            showConfirmButton: false,
            timer: 1000
          })

          this.router.navigate(['login'])

        },
        (error) => {
          console.log(error.error.message);
        }

    )


  }

}
