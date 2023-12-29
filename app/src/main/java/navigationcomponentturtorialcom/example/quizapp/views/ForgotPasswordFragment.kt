package navigationcomponentturtorialcom.example.quizapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import navigationcomponentturtorialcom.example.quizapp.R
import navigationcomponentturtorialcom.example.quizapp.repository.AuthRepository
import navigationcomponentturtorialcom.example.quizapp.viewmodel.AuthViewModel
import navigationcomponentturtorialcom.example.quizapp.viewmodel.AuthViewModelFactory

class ForgotPasswordFragment : Fragment() {
    private val viewModel by viewModels<AuthViewModel> {
        AuthViewModelFactory(AuthRepository())
    }

    private lateinit var navController: NavController
    private lateinit var etEmailSendPassword: TextInputEditText
    private lateinit var btnSend: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnSend = view.findViewById<Button>(R.id.btnSend)
        etEmailSendPassword = view.findViewById<TextInputEditText>(R.id.etEmailSendPassword)

        btnSend.setOnClickListener {
            val email = etEmailSendPassword.text.toString()
            viewModel.sendEmailToResetPassword(email)
            navController.navigate(R.id.action_forgotPasswordFragment_to_signInFragment)
        }
    }
}