package com.example.rickandmortyproject.base

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.rickandmortyproject.presentation.MainActivity
import com.example.rickandmortyproject.R
import com.example.rickandmortyproject.utils.FragmentTransfer
import com.example.rickandmortyproject.data.model.Model
import com.example.rickandmortyproject.di.App
import com.example.rickandmortyproject.di.DependenciesProvider
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(
    @LayoutRes private val layoutRes: Int,
    private val viewModelClass: Class<VM>,
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = checkNotNull(_binding) { "Binding isn't init" }

    private var _viewModel: VM? = null
    val viewModel get() = checkNotNull(_viewModel) { "ViewModel isn't init" }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @CallSuper
    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDaggerComponent((requireActivity().applicationContext as App).getDependenciesProvider())
        _viewModel = ViewModelProvider(this, viewModelFactory)[viewModelClass]
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(layoutRes, container, false)
        _binding = initBinding(view)
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpButton(showUpButton())
        activity?.setTitle(setFragmentTitle())
    }

    open fun setMenu(clazz: Class<out Model>, setSearchButton: (MenuItem) -> Unit) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.app_menu, menu)
                setSearchButton(menu.findItem(R.id.action_search))
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.action_filter) {
                    setFilterButton(clazz)
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setFilterButton(clazz: Class<out Model>) {
        FragmentTransfer().goToFilterView(clazz, parentFragmentManager)
    }

    private fun setUpButton(boolean: Boolean) {
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(boolean)
    }

    @StringRes
    abstract fun setFragmentTitle(): Int

    abstract fun initBinding(view: View): VB

    abstract fun initDaggerComponent(dependenciesProvider: DependenciesProvider)

    abstract fun showUpButton(): Boolean
}