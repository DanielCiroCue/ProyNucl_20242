import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthServiceService } from '../../sevices/auth-service.service';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { LoginDTO } from '../../model/login-dto-model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  loginForm: FormGroup;

  constructor(private fb: FormBuilder,
    private authService: AuthServiceService,
    private router: Router,
    private messageService: MessageService) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const loginDTO : LoginDTO = this.loginForm.value;
      this.authService.login(loginDTO).subscribe({
        next: (data) => {
          sessionStorage.setItem('rol', data.rol);

          if (data.rol==='MASTER') {
            this.router.navigate(['/gestion/solicitud']);            
          }else{
            this.router.navigate(['/public/login']); 
          }
        },
        error: (er) => {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: er.error['message'] });
        }
      });
    }
  }
}
