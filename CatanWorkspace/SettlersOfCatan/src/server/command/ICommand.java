package server.command;

public interface ICommand<T> {
	T execute(T t);
}