import React, { Component } from 'react';
import { connect } from '98k';
import { Link } from 'react-router-dom';

class Header extends Component {
  state = {
    term: '',
  }

  render() {
    const { user } = this.props;
    const { term } = this.state;

    return (
      <div className='bg-light navbar navbar-expand-lg navbar-light row'>
        <div className='col-2'>
          <Link to='/'>
            <img className='w-100' src='/Activity/assets/7108c7bdb9210263b625c13f8503bf2d.png'/>
          </Link>
        </div>
        <form className='col-3 offset-5' onSubmit={this.submit}>
          <input 
            className='form-control'
            Placeholder='search activities here'
            value={term}
            onChange={this.change}
          />
        </form>
        <div className='col-2 text-center'>
          {user ? (
            <Link to='/self'>{user.name}</Link>
          ) : (
            <div><Link to='/register'>register</Link> / <Link to='/login'>login</Link></div>
          )}
        </div>
      </div>
    );
  }

  change = e => {
    this.setState({
      term: e.target.value,
    });
  }

  submit = e => {
    e.preventDefault();
    //this.props.dispatch({
    //type    : 'home/fetchSearch',
    //payload : this.state.term,
    //});
    //this.props.history.push('/search');
  }
}

export default connect(state => ({
  user: state.auth.user,
}))(Header);